package com.entvy.openbidhub.service;

import com.entvy.openbidhub.domain.AuctionItemEntity;
import com.entvy.openbidhub.domain.OnbidRawItemEntity;
import com.entvy.openbidhub.dto.AuctionCardDto;
import com.entvy.openbidhub.dto.RegionBidSummary;
import com.entvy.openbidhub.mapper.AuctionItemMapper;
import com.entvy.openbidhub.mapper.OnbidRawItemMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AuctionItemService {
    private final AuctionItemMapper auctionItemMapper;
    private final OnbidRawItemMapper rawItemMapper;

    public AuctionItemService(AuctionItemMapper auctionItemMapper, OnbidRawItemMapper rawItemMapper) {
        this.auctionItemMapper = auctionItemMapper;
        this.rawItemMapper = rawItemMapper;
    }

    public int processAndSave() {
        List<OnbidRawItemEntity> rawItems = rawItemMapper.findAll();
        List<AuctionItemEntity> processed = filterLatestItems(rawItems);
        auctionItemMapper.insertItems(processed);
        return processed.size();
    }

    private AuctionItemEntity convertToAuctionItem(OnbidRawItemEntity item) {
        LocalDateTime begn = item.getPbctBegnDtm();
        LocalDateTime cls = item.getPbctClsDtm();

        // 문자열로 들어오는 경우 파싱
        if (begn == null && item.getRawPbctBegnDtm() != null) {
            begn = parseKoreanDate(item.getRawPbctBegnDtm());
        }
        if (cls == null && item.getRawPbctClsDtm() != null) {
            cls = parseKoreanDate(item.getRawPbctClsDtm());
        }

        if (begn == null || cls == null) {
            log.warn("입찰 날짜 누락으로 제외됨: cltrNo={}, pbctNo={}", item.getCltrNo(), item.getPbctNo());
            return null;
        }

        AuctionCardDto dto = new AuctionCardDto(
                item.getCltrNm(),
                item.getPbctCltrStatNm(),
                item.getApslAsesAvgAmt(),
                item.getMinBidPrc(),
                begn.toLocalDate(),
                cls.toLocalDate(),
                item.getCltrMnmtNo()
        );
        return dto.toEntity(item.getCltrNo(), item.getPbctNo());
    }

    private List<AuctionItemEntity> filterLatestItems(List<OnbidRawItemEntity> rawItems) {
        Map<String, OnbidRawItemEntity> latestMap = new HashMap<>();
        for (OnbidRawItemEntity item : rawItems) {
            if(item.getCltrNo() == null || item.getPbctBegnDtm() == null) continue;
            latestMap.compute(item.getCltrNo(), (key, existing) ->
                    (existing == null || item.getPbctBegnDtm().isAfter(existing.getPbctBegnDtm())) ? item : existing
            );
        }

        log.info("원본 {}건 중 중복 제거 후 {}건 처리됨", rawItems.size(), latestMap.size());

        return latestMap.values().stream()
                .map(this::convertToAuctionItem)
                .filter(Objects::nonNull)
                .toList();
    }
    private LocalDateTime parseKoreanDate(String raw) {
        if (raw == null || raw.isBlank()) return null;
        raw = raw.trim();

        try {
            // 오전/오후가 포함된 경우
            if (raw.contains("오전") || raw.contains("오후")) {
                String normalized = raw
                        .replace("오전", "AM")
                        .replace("오후", "PM");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm:ss", Locale.KOREAN);
                return LocalDateTime.parse(normalized, formatter);
            }

            // 일반적인 24시간제 형식
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return LocalDateTime.parse(raw, formatter);

        } catch (Exception e) {
            log.warn("날짜 파싱 실패: {}", raw);
            return null;
        }
    }
    // 지역별 입찰 수량 집계
    public List<RegionBidSummary> getRegionBidSummary() {
        return auctionItemMapper.getRegionBidSummary();
    }
    // 입찰 상태별 건수 집계
    public Map<String, Long> getBidStatusSummary() {
        List<Map<String, Object>> raw = auctionItemMapper.selectBidStatusSummary();
        return raw.stream()
                .collect(Collectors.toMap(
                        m -> (String) m.get("status"),
                        m -> ((Number) m.get("count")).longValue()
                ));
    }
}