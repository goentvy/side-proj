package com.entvy.openbidhub.dto;

import com.entvy.openbidhub.domain.AuctionItemEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "경매 카드 응답 DTO")
public class AuctionCardDto {
    @Schema(description = "물건명", example = "경기도 남양주시 화도읍 묵현리 241-11")
    private String cltrNm;

    @Schema(description = "입찰 상태", example = "입찰준비중")
    private String pbctCltrStatNm;

    @Schema(description = "감정가", example = "19025880")
    private Long apslAsesAvgAmt;

    @Schema(description = "최저 입찰가", example = "1903000")
    private Long minBidPrc;

    @Schema(description = "입찰 시작일", example = "2025-09-01")
    private LocalDate pbctBegnDtm;

    @Schema(description = "입찰 마감일", example = "2025-09-10")
    private LocalDate pbctClsDtm;

    @Schema(description = "관리번호", example = "202509964001")
    private String cltrMnmtNo;


    public AuctionItemEntity toEntity(String cltrNo, String pbctNo) {
        return AuctionItemEntity.builder()
                .cltrNm(cltrNm)
                .pbctCltrStatNm(pbctCltrStatNm)
                .apslAsesAvgAmt(apslAsesAvgAmt)
                .minBidPrc(minBidPrc)
                .pbctBegnDtm(pbctBegnDtm.atStartOfDay())
                .pbctClsDtm(pbctClsDtm.atStartOfDay())
                .cltrMnmtNo(cltrMnmtNo)
                .cltrNo(cltrNo)
                .pbctNo(pbctNo)
                .source(AuctionItemEntity.SOURCE_OPENAPI)
                .build();
    }
}