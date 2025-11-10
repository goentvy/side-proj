package com.entvy.openbidhub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuctionCardDto {
    private String cltrNm;              // 물건명
    private String pbctCltrStatNm;      // 입찰상태
    private Long apslAsesAvgAmt;        // 감정가
    private Long minBidPrc;             // 입찰가
    private String pbctBegnDtm;         // 입찰 시작일
    private String pbctClsDtm;          // 입찰 마감일
    private String cltrMnmtNo;          // 관리번호
}