package com.entvy.openbidhub.openapi.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class OnbidItemRawDto {
    @XmlElement(name = "CLTR_NO")
    private String cltrNo;

    @XmlElement(name = "PBCT_NO")
    private String pbctNo;

    @XmlElement(name = "CLTR_NM")
    private String cltrNm;

    @XmlElement(name = "PBCT_BEGN_DTM")
    private String pbctBegnDtm;

    @XmlElement(name = "PBCT_CLS_DTM")
    private String pbctClsDtm;

    @XmlElement(name = "MIN_BID_PRC")
    private String minBidPrc;

    @XmlElement(name = "APSL_ASES_AVG_AMT")
    private String apslAsesAvgAmt;

    @XmlElement(name = "PBCT_CLTR_STAT_NM")
    private String pbctCltrStatNm;

    @XmlElement(name = "CLTR_MNMT_NO")
    private String cltrMnmtNo;

    // 전체데이터 삽입용 추가 필드
    @XmlElement(name = "RNUM") private Integer rnum;
    @XmlElement(name = "PLNM_NO") private String plnmNo;
    @XmlElement(name = "PBCT_CDTN_NO") private String pbctCdtnNo;
    @XmlElement(name = "CLTR_HSTR_NO") private String cltrHstrNo;
    @XmlElement(name = "SCRN_GRP_CD") private String scrnGrpCd;
    @XmlElement(name = "CTGR_FULL_NM") private String ctgrFullNm;
    @XmlElement(name = "BID_MNMT_NO") private String bidMnmtNo;
    @XmlElement(name = "LDNM_ADRS") private String ldnmAdrs;
    @XmlElement(name = "NMRD_ADRS") private String nmrdAdrs;
    @XmlElement(name = "LDNM_PNU") private String ldnmPnu;
    @XmlElement(name = "DPSL_MTD_CD") private String dpslMtdCd;
    @XmlElement(name = "DPSL_MTD_NM") private String dpslMtdNm;
    @XmlElement(name = "BID_MTD_NM") private String bidMtdNm;
    @XmlElement(name = "FEE_RATE") private String feeRate;
    @XmlElement(name = "USCBD_CNT") private String uscbCnt;
    @XmlElement(name = "IQRY_CNT") private String iqryCnt;
    @XmlElement(name = "GOODS_NM") private String goodsNm;
    @XmlElement(name = "MANF") private String manf;
    @XmlElement(name = "MDL") private String mdl;
    @XmlElement(name = "NRGT") private String nrgt;
    @XmlElement(name = "GRBX") private String grbx;
    @XmlElement(name = "ENDPC") private String endpc;
    @XmlElement(name = "VHCL_MLGE") private String vhclMlge;
    @XmlElement(name = "FUEL") private String fuel;
    @XmlElement(name = "SCRT_NM") private String scrtNm;
    @XmlElement(name = "TPBZ") private String tpbz;
    @XmlElement(name = "ITM_NM") private String itmNm;
    @XmlElement(name = "MMB_RGT_NM") private String mmbRgtNm;
    @XmlElement(name = "CLTR_IMG_FILES") private String cltrImgFiles;
}
