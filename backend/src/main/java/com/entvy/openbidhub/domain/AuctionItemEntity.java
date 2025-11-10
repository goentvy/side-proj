package com.entvy.openbidhub.domain;

import com.entvy.openbidhub.dto.AuctionCardDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "auction_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuctionItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cltrNm;           // 물건명
    private String pbctCltrStatNm;   // 입찰상태
    private Long apslAsesAvgAmt;     // 감정가
    private Long minBidPrc;          // 입찰가
    private String pbctBegnDtm;      // 입찰 시작일
    private String pbctClsDtm;       // 입찰 마감일
    private String cltrMnmtNo;       // 관리번호

    @Column(nullable = false, updatable = false)
    private String cltrNo;           // 중복 제거용 기준키

    @Column(nullable = false, updatable = false)
    private String pbctNo;           // 공매번호

    @Column(updatable = false)
    private String source;           // 데이터 출처 (예: "openapi.onbid")

    private String createdAt;
    private String updatedAt;

    public static AuctionItemEntity from(AuctionCardDto dto, String cltrNo, String pbctNo) {
        return AuctionItemEntity.builder()
                .cltrNm(dto.getCltrNm())
                .pbctCltrStatNm(dto.getPbctCltrStatNm())
                .apslAsesAvgAmt(dto.getApslAsesAvgAmt())
                .minBidPrc(dto.getMinBidPrc())
                .pbctBegnDtm(dto.getPbctBegnDtm())
                .pbctClsDtm(dto.getPbctClsDtm())
                .cltrMnmtNo(dto.getCltrMnmtNo())
                .cltrNo(cltrNo)
                .pbctNo(pbctNo)
                .source("openapi.onbid")
                .createdAt(LocalDateTime.now().toString())
                .updatedAt(LocalDateTime.now().toString())
                .build();
    }
}
