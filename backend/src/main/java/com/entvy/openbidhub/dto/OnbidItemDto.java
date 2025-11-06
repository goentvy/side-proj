package com.entvy.openbidhub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnbidItemDto {
    private String cltrNm;
    private String cltrMnmtNo;
    private String ldnmAdrs;
    private String nmrdAdrs;
    private String ldnmPnu;
    private String ctgrFullNm;
    private String dpslMtdNm;
    private String bidMtdNm;
    private String minBidPrc;
    private String apslAsesAvgAmt;
    private String feeRate;
    private String pbctBegnDtm;
    private String pbctClsDtm;
    private String pbctCltrStatNm;
    private String goodsNm;
    private String iqryCnt;
    private String cltrImgFiles;

    private String plnmNo;
    private String pbctNo;
    private String pbctCdtnNo;
    private String cltrNo;
    private String cltrHstrNo;
    private String scrnGrpCd;
    private String bidMnmtNo;
    private String dpslMtdCd;
    private String uscbCnt;

    private String manf;
    private String mdl;
    private String nrgt;
    private String grbx;
    private String endpc;
    private String vhclMlge;
    private String fuel;

    private String sido;
    private String sgk;
    private String emd;

    private Long goodsPrice;
    private Long openPrice;

    private String pbctBegnDt;
    private String pbctClsDt;

    private String bidStatus;
}

