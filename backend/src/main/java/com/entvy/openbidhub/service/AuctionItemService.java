package com.entvy.openbidhub.service;

import com.entvy.openbidhub.domain.AuctionItemEntity;
import com.entvy.openbidhub.dto.AuctionCardDto;
import com.entvy.openbidhub.mapper.AuctionItemMapper;
import com.entvy.openbidhub.openapi.xml.OnbidItemRawDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Slf4j
@Service
public class AuctionItemService {

    private final OnbidXmlParserService parser;
    private final OnbidApiService apiClient;
    private final AuctionItemMapper auctionItemMapper;

    public AuctionItemService(OnbidXmlParserService parser,
                              OnbidApiService apiClient,
                              AuctionItemMapper auctionItemMapper) {
        this.parser = parser;
        this.apiClient = apiClient;
        this.auctionItemMapper = auctionItemMapper;
    }

    public void saveAuctionItems() {
        String xml = apiClient.fetchRawXml();
        List<OnbidItemRawDto> rawDtos = parser.parse(xml);
        if (rawDtos == null || rawDtos.isEmpty()) {
            log.warn("파싱된 결과가 없습니다. XML: {}", xml);
            return;
        }

        // 중복 제거
        Map<String, OnbidItemRawDto> latestMap = new HashMap<>();
        for (OnbidItemRawDto dto : rawDtos) {
            String cltrNo = dto.getCltrNo();
            OnbidItemRawDto existing = latestMap.get(cltrNo);
            if (existing == null || dto.getPbctBegnDtm().compareTo(existing.getPbctBegnDtm()) > 0) {
                latestMap.put(cltrNo, dto);
            }
        }

        // DTO → Entity 변환
        List<AuctionItemEntity> entities = latestMap.values().stream()
                .map(dto -> AuctionItemEntity.from(
                        new AuctionCardDto(
                                dto.getCltrNm(),
                                dto.getPbctCltrStatNm(),
                                parseLong(dto.getApslAsesAvgAmt()),
                                parseLong(dto.getMinBidPrc()),
                                formatDate(dto.getPbctBegnDtm()),
                                formatDate(dto.getPbctClsDtm()),
                                dto.getCltrMnmtNo()
                        ),
                        dto.getCltrNo(),
                        dto.getPbctNo()
                ))
                .toList();

        auctionItemMapper.insertItems(entities);
    }

    private Long parseLong(String value) {
        try {
            return Long.parseLong(value.replaceAll(",", "").trim());
        } catch (NumberFormatException e) {
            return null; // 또는 0L, 예외 처리 방식에 따라
        }
    }
    private String formatDate(String rawDateTime) {
        if (rawDateTime == null || rawDateTime.isBlank()) return null;
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            LocalDateTime dateTime = LocalDateTime.parse(rawDateTime, inputFormatter);

            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yy.MM.dd a hh시", Locale.KOREAN);
            return dateTime.format(outputFormatter);
        } catch (DateTimeParseException e) {
            return rawDateTime; // 실패 시 원본 반환
        }

    }
}