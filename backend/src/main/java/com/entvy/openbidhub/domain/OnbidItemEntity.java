package com.entvy.openbidhub.domain;

import com.entvy.openbidhub.dto.OnbidItemDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "onbid_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OnbidItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Column(columnDefinition = "TEXT")
    private String goodsNm;

    private String iqryCnt;

    @Column(columnDefinition = "TEXT")
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

    // ✅ 추가된 필드
    private String sido;
    private String sgk;
    private String emd;

    private Long goodsPrice;
    private Long openPrice;

    private String pbctBegnDt;
    private String pbctClsDt;

    private String bidStatus;

    public static OnbidItemEntity from(OnbidItemDto dto) {
        return OnbidItemEntity.builder()
                .cltrNm(dto.getCltrNm())
                .cltrMnmtNo(dto.getCltrMnmtNo())
                .ldnmAdrs(dto.getLdnmAdrs())
                .nmrdAdrs(dto.getNmrdAdrs())
                .ldnmPnu(dto.getLdnmPnu())
                .ctgrFullNm(dto.getCtgrFullNm())
                .dpslMtdNm(dto.getDpslMtdNm())
                .bidMtdNm(dto.getBidMtdNm())
                .minBidPrc(dto.getMinBidPrc())
                .apslAsesAvgAmt(dto.getApslAsesAvgAmt())
                .feeRate(dto.getFeeRate())
                .pbctBegnDtm(dto.getPbctBegnDtm())
                .pbctClsDtm(dto.getPbctClsDtm())
                .pbctCltrStatNm(dto.getPbctCltrStatNm())
                .goodsNm(dto.getGoodsNm())
                .iqryCnt(dto.getIqryCnt())
                .cltrImgFiles(dto.getCltrImgFiles())
                .plnmNo(dto.getPlnmNo())
                .pbctNo(dto.getPbctNo())
                .pbctCdtnNo(dto.getPbctCdtnNo())
                .cltrNo(dto.getCltrNo())
                .cltrHstrNo(dto.getCltrHstrNo())
                .scrnGrpCd(dto.getScrnGrpCd())
                .bidMnmtNo(dto.getBidMnmtNo())
                .dpslMtdCd(dto.getDpslMtdCd())
                .uscbCnt(dto.getUscbCnt())
                .manf(dto.getManf())
                .mdl(dto.getMdl())
                .nrgt(dto.getNrgt())
                .grbx(dto.getGrbx())
                .endpc(dto.getEndpc())
                .vhclMlge(dto.getVhclMlge())
                .fuel(dto.getFuel())

                // ✅ 추가된 매핑
                .sido(dto.getSido())
                .sgk(dto.getSgk())
                .emd(dto.getEmd())
                .goodsPrice(dto.getGoodsPrice())
                .openPrice(dto.getOpenPrice())
                .pbctBegnDt(dto.getPbctBegnDt())
                .pbctClsDt(dto.getPbctClsDt())
                .bidStatus(dto.getBidStatus())

                .build();
    }
}