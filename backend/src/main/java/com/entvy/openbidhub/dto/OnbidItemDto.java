package com.entvy.openbidhub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "온비드 원시 경매 데이터 DTO")
public class OnbidItemDto {

    @Schema(description = "물건명", example = "서울시 강남구 역삼동 123-45")
    private String cltrNm;

    @Schema(description = "관리번호", example = "202509964001")
    private String cltrMnmtNo;

    @Schema(description = "도로명 주소", example = "서울특별시 강남구 테헤란로 123")
    private String ldnmAdrs;

    @Schema(description = "지번 주소", example = "서울특별시 강남구 역삼동 456-78")
    private String nmrdAdrs;

    @Schema(description = "도로명 고유번호", example = "116801010010123")
    private String ldnmPnu;

    @Schema(description = "카테고리 전체명", example = "자동차 > 승용차 > 중형")
    private String ctgrFullNm;

    @Schema(description = "처분 방식", example = "일괄처분")
    private String dpslMtdNm;

    @Schema(description = "입찰 방식", example = "인터넷입찰")
    private String bidMtdNm;

    @Schema(description = "최저 입찰가", example = "1000000")
    private String minBidPrc;

    @Schema(description = "감정가", example = "1200000")
    private String apslAsesAvgAmt;

    @Schema(description = "수수료율", example = "2.5")
    private String feeRate;

    @Schema(description = "입찰 시작일시", example = "2025-09-01T14:00:00")
    private String pbctBegnDtm;

    @Schema(description = "입찰 마감일시", example = "2025-09-10T14:00:00")
    private String pbctClsDtm;

    @Schema(description = "입찰 상태", example = "입찰준비중")
    private String pbctCltrStatNm;

    @Schema(description = "물품명", example = "현대 아반떼 AD")
    private String goodsNm;

    @Schema(description = "조회수", example = "123")
    private String iqryCnt;

    @Schema(description = "물건 이미지 파일 경로", example = "https://onbid.or.kr/images/item123.jpg")
    private String cltrImgFiles;

    @Schema(description = "계획번호")
    private String plnmNo;

    @Schema(description = "입찰번호")
    private String pbctNo;

    @Schema(description = "입찰조건번호")
    private String pbctCdtnNo;

    @Schema(description = "물건번호")
    private String cltrNo;

    @Schema(description = "물건이력번호")
    private String cltrHstrNo;

    @Schema(description = "화면그룹코드")
    private String scrnGrpCd;

    @Schema(description = "입찰관리번호")
    private String bidMnmtNo;

    @Schema(description = "처분방식코드")
    private String dpslMtdCd;

    @Schema(description = "사용자 조회수")
    private String uscbCnt;

    @Schema(description = "제조사", example = "현대")
    private String manf;

    @Schema(description = "모델명", example = "아반떼 AD")
    private String mdl;

    @Schema(description = "배기량", example = "1600")
    private String nrgt;

    @Schema(description = "변속기", example = "자동")
    private String grbx;

    @Schema(description = "최종가격", example = "1100000")
    private String endpc;

    @Schema(description = "주행거리", example = "85000")
    private String vhclMlge;

    @Schema(description = "연료 종류", example = "가솔린")
    private String fuel;

    @Schema(description = "시도", example = "서울특별시")
    private String sido;

    @Schema(description = "시군구", example = "강남구")
    private String sgk;

    @Schema(description = "읍면동", example = "역삼동")
    private String emd;

    @Schema(description = "물품 가격", example = "1200000")
    private Long goodsPrice;

    @Schema(description = "공개 가격", example = "1000000")
    private Long openPrice;

    @Schema(description = "입찰 시작일", example = "2025-09-01")
    private String pbctBegnDt;

    @Schema(description = "입찰 마감일", example = "2025-09-10")
    private String pbctClsDt;

    @Schema(description = "입찰 상태", example = "인터넷입찰진행중")
    private String bidStatus;
}