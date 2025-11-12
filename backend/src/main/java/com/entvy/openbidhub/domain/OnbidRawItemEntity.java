package com.entvy.openbidhub.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OnbidRawItemEntity {
    private Long id;
    private Integer rnum;
    private String plnmNo;
    private String pbctNo;
    private String pbctCdtnNo;
    private String cltrNo;
    private String cltrHstrNo;
    private String scrnGrpCd;
    private String ctgrFullNm;
    private String bidMnmtNo;
    private String cltrNm;
    private String cltrMnmtNo;
    private String ldnmAdrs;
    private String nmrdAdrs;
    private String ldnmPnu;
    private String dpslMtdCd;
    private String dpslMtdNm;
    private String bidMtdNm;
    private Long minBidPrc;
    private Long apslAsesAvgAmt;
    private String feeRate;
    private LocalDateTime pbctBegnDtm;
    private LocalDateTime pbctClsDtm;
    private String pbctCltrStatNm;
    private Integer uscbCnt;
    private Integer iqryCnt;
    private String goodsNm;
    private String manuf;
    private String mdl;
    private String nrgt;
    private String grbx;
    private String endpc;
    private String vhclMlge;
    private String fuel;
    private String scrtNm;
    private String tpbz;
    private String itmNm;
    private String mmbRgtNm;
    private String cltrImgFiles;
    private LocalDateTime createdAt;
    // 날짜가 문자열로 들어오는 경우
    private String rawPbctBegnDtm;
    private String rawPbctClsDtm;
}
