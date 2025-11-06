package com.entvy.openbidhub.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnbidItemSearchCondition {
    private String sido;              // 시도
    private String sgk;               // 시군구
    private String emd;               // 읍면동
    private String cltrNm;           // 물건명
    private String bidStatus;        // 입찰상태
    private Long openPriceFrom;      // 시작가 하한
    private Long openPriceTo;        // 시작가 상한
    private String pbctBegnDtFrom;   // 입찰시작일 (yyyyMMdd)
    private String pbctBegnDtTo;     // 입찰종료일
}
