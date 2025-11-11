package com.entvy.openbidhub.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnbidItemSearchCondition {
    private String sido;                // 시도
    private String sgk;                 // 시군구
    private String emd;                 // 읍면동
    private String cltrNm;             // 물건명
    private String cltrMnmtNo;         // 물건관리번호
    private String pbctCltrStatNm;     // 입찰 상태
    private Long minBidPrcFrom;        // 입찰가 하한
    private Long minBidPrcTo;          // 입찰가 상한
    private Long apslAsesAvgAmtFrom;   // 감정가 하한
    private Long apslAsesAvgAmtTo;     // 감정가 상한
    private String pbctBegnDtmFrom;    // 입찰 시작일시 (예: 25.09.01 오전 02시)
    private String pbctBegnDtmTo;      // 입찰 종료일시
}