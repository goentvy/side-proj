package com.entvy.openbidhub.service;

import com.entvy.openbidhub.domain.OnbidItemEntity;
import com.entvy.openbidhub.dto.OnbidItemDto;
import com.entvy.openbidhub.repository.OnbidItemRepository;
import com.entvy.openbidhub.util.DateRangeUtil;
import com.entvy.openbidhub.util.XmlParser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OnbidSyncService {

    private final OnbidApiService apiService;
    private final OnbidItemRepository repository;

    @Transactional
    public void sync() throws Exception {
        String xml = apiService.fetchRawData();
        List<OnbidItemDto> items = XmlParser.parse(xml);
        List<OnbidItemEntity> entities = items.stream()
                .map(OnbidItemEntity::from)
                .collect(Collectors.toList());

        repository.saveAll(entities);
    }

    @Transactional
    public void syncAll() {
        Map<String, String> range = DateRangeUtil.getSixMonthRange();
        String fromDate = range.get("from");
        String toDate = range.get("to");

        int pageNo = 1;
        int numOfRows = 100;
        int totalCount;

        try {
            do {
                String xml = apiService.fetchPage(pageNo, numOfRows, fromDate, toDate);
                List<OnbidItemDto> dtos = XmlParser.parse(xml);
                List<OnbidItemEntity> entities = dtos.stream()
                        .filter(dto -> !repository.existsByCltrMnmtNo(dto.getCltrMnmtNo()))
                        .map(OnbidItemEntity::from)
                        .toList();

                repository.saveAll(entities);
                totalCount = XmlParser.extractTotalCount(xml);
                pageNo++;
            } while ((pageNo - 1) * numOfRows < totalCount);

        } catch (Exception e) {
            log.error("전체 동기화 실패", e);
            throw new RuntimeException("전체 동기화 중 오류 발생");
        }
    }
}
