package com.entvy.openbidhub.dto;

import com.entvy.openbidhub.domain.OnbidItemEntity;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Builder
@JsonDeserialize(builder = OnbidItemResponse.OnbidItemResponseBuilder.class)
@Schema(description = "온비드 경매 요약 응답 DTO")
public class OnbidItemResponse {

    @Schema(description = "관리번호", example = "202509964001")
    private final String cltrMnmtNo;

    @Schema(description = "물건명", example = "서울시 강남구 역삼동 123-45")
    private final String cltrNm;

    @Schema(description = "시도", example = "서울특별시")
    private final String sido;

    @Schema(description = "시군구", example = "강남구")
    private final String sgk;

    @Schema(description = "읍면동", example = "역삼동")
    private final String emd;

    @Schema(description = "감정가", example = "12000000")
    private final Long goodsPrice;

    @Schema(description = "시작가", example = "10000000")
    private final Long openPrice;

    @Schema(description = "입찰 시작일 (yyyyMMdd)", example = "20250901")
    private final String pbctBegnDt;

    @Schema(description = "입찰 마감일 (yyyyMMdd)", example = "20250910")
    private final String pbctClsDt;

    @Schema(description = "입찰 상태", example = "인터넷입찰진행중")
    private final String bidStatus;

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

    @JsonPOJOBuilder(withPrefix = "")
    public static class OnbidItemResponseBuilder {
    }
}