package com.entvy.openbidhub.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDetailDto {
    private String cltrNm;
    private String ldnmAdrs;
    private String nmrdAdrs;
    private String cltrMnmtNo;
    private String ctgrFullNm;
    private String scrtNm;
    private String pbctNo;
    private String pbctCdtnNo;
    private String cltrNo;
    private String cltrHstrNo;

    private Long minBidPrc;
    private Long apslAsesAvgAmt;
    private String feeRate;
    private LocalDateTime pbctBegnDtm;
    private LocalDateTime pbctClsDtm;
    private String pbctCltrStatNm;
    private Integer uscbCnt;
    private Integer iqryCnt;

    private String goodsNm;
    private String tpbz;
    private String mmbRgtNm;

    private String cltrImgFiles;
    private List<String> cltrImgFileList;
}