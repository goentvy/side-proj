package com.entvy.openbidhub.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "onbid_raw_items")
@Getter
@Setter
@NoArgsConstructor
public class OnbidRawItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Column(columnDefinition = "TEXT")
    private String goodsNm;
    private String tpbz;
    private String mmbRgtNm;

    @Column(columnDefinition = "TEXT")
    private String cltrImgFiles;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
