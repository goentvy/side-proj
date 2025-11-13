package com.entvy.openbidhub.service;

import com.entvy.openbidhub.domain.OnbidRawItemEntity;
import com.entvy.openbidhub.mapper.OnbidRawItemMapper;
import com.entvy.openbidhub.openapi.xml.OnbidItemRawDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OnbidRawItemService {

    private final OnbidApiService apiClient;
    private final OnbidXmlParserService parser;
    private final OnbidRawItemMapper rawItemMapper;

    public int saveRawItems() {
        List<String> xmlPages = apiClient.fetchAllRawXml();
        List<OnbidItemRawDto> allDtos = new ArrayList<>();

        for (String xml : xmlPages) {
            List<OnbidItemRawDto> parsed = parser.parse(xml);
            if (parsed != null) allDtos.addAll(parsed);
        }

        List<OnbidRawItemEntity> entities = allDtos.stream()
                .map(dto -> OnbidRawItemEntity.builder()
                        .rnum(dto.getRnum())
                        .plnmNo(dto.getPlnmNo())
                        .pbctNo(dto.getPbctNo())
                        .pbctCdtnNo(dto.getPbctCdtnNo())
                        .cltrNo(dto.getCltrNo())
                        .cltrHstrNo(dto.getCltrHstrNo())
                        .scrnGrpCd(dto.getScrnGrpCd())
                        .ctgrFullNm(dto.getCtgrFullNm())
                        .bidMnmtNo(dto.getBidMnmtNo())
                        .cltrNm(dto.getCltrNm())
                        .cltrMnmtNo(dto.getCltrMnmtNo())
                        .ldnmAdrs(dto.getLdnmAdrs())
                        .nmrdAdrs(dto.getNmrdAdrs())
                        .ldnmPnu(dto.getLdnmPnu())
                        .dpslMtdCd(dto.getDpslMtdCd())
                        .dpslMtdNm(dto.getDpslMtdNm())
                        .bidMtdNm(dto.getBidMtdNm())
                        .minBidPrc(parseLong(dto.getMinBidPrc()))
                        .apslAsesAvgAmt(parseLong(dto.getApslAsesAvgAmt()))
                        .feeRate(dto.getFeeRate())
                        .pbctBegnDtm(formatDate(dto.getPbctBegnDtm()))
                        .pbctClsDtm(formatDate(dto.getPbctClsDtm()))
                        .pbctCltrStatNm(dto.getPbctCltrStatNm())
                        .uscbCnt(parseInt(dto.getUscbCnt()))
                        .iqryCnt(parseInt(dto.getIqryCnt()))
                        .goodsNm(dto.getGoodsNm())
                        .manuf(dto.getManf())
                        .mdl(dto.getMdl())
                        .nrgt(dto.getNrgt())
                        .grbx(dto.getGrbx())
                        .endpc(dto.getEndpc())
                        .vhclMlge(dto.getVhclMlge())
                        .fuel(dto.getFuel())
                        .scrtNm(dto.getScrtNm())
                        .tpbz(dto.getTpbz())
                        .itmNm(dto.getItmNm())
                        .mmbRgtNm(dto.getMmbRgtNm())
                        .cltrImgFiles(dto.getCltrImgFiles())
                        .build())
                .toList();

        rawItemMapper.insertAll(entities);
        return entities.size();
    }

    private Long parseLong(String value) {
        try {
            return Long.parseLong(value.trim());
        } catch (Exception e) {
            return null;
        }
    }

    private Integer parseInt(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (Exception e) {
            return null;
        }
    }

    private LocalDateTime formatDate(String value) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            return LocalDateTime.parse(value.trim(), formatter);
        } catch (Exception e) {
            return null;
        }
    }
}
