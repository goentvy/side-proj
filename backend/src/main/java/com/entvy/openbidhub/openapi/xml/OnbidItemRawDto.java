package com.entvy.openbidhub.openapi.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Getter;

@XmlAccessorType(XmlAccessType.FIELD)
@Getter
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
}
