package com.entvy.openbidhub.dto;

import com.entvy.openbidhub.domain.OnbidItemEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OnbidItemResponse {
    private String cltrMnmtNo;     // 관리번호
    private String cltrNm;         // 물건명
    private String sido;           // 시도
    private String sgk;            // 시군구
    private String emd;            // 읍면동
    private Long goodsPrice;       // 감정가
    private Long openPrice;        // 시작가
    private String pbctBegnDt;     // 입찰시작일 (yyyyMMdd)
    private String pbctClsDt;      // 입찰마감일
    private String bidStatus;      // 입찰상태

    public static OnbidItemResponse from(OnbidItemEntity entity) {
        return OnbidItemResponse.builder()
                .cltrMnmtNo(entity.getCltrMnmtNo())
                .cltrNm(entity.getCltrNm())
                .sido(entity.getSido())
                .sgk(entity.getSgk())
                .emd(entity.getEmd())
                .goodsPrice(entity.getGoodsPrice())
                .openPrice(entity.getOpenPrice())
                .pbctBegnDt(entity.getPbctBegnDt())
                .pbctClsDt(entity.getPbctClsDt())
                .bidStatus(entity.getBidStatus())
                .build();
    }
}
