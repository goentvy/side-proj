package com.entvy.openbidhub.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(description = "경매 물건 검색 조건")
public class OnbidItemSearchCondition {

    @Schema(description = "물건명", example = "서울시 강남구 역삼동 123-45")
    private String cltrNm;

    @Schema(description = "관리번호", example = "202509964001")
    private String cltrMnmtNo;

    @Schema(description = "입찰 상태", example = "인터넷입찰진행중")
    private String pbctCltrStatNm;

    @Schema(description = "최저 입찰가", example = "1000000")
    private Long minBidPrcFrom;

    @Schema(description = "최고 입찰가", example = "5000000")
    private Long minBidPrcTo;

    @Schema(description = "감정가 시작", example = "2000000")
    private Long apslAsesAvgAmtFrom;

    @Schema(description = "감정가 끝", example = "8000000")
    private Long apslAsesAvgAmtTo;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Schema(description = "입찰 시작일", example = "2025-09-01T14:00:00")
    private LocalDateTime pbctBegnDtmFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Schema(description = "입찰 종료일", example = "2025-09-10T14:00:00")
    private LocalDateTime pbctBegnDtmTo;

    @Schema(description = "정렬 컬럼", example = "minBidPrc")
    private String sortColumn;

    @Schema(description = "정렬 방향", example = "DESC")
    private String sortDirection;

    public String getSafeSortColumn() {
        return SortColumn.resolve(sortColumn);
    }

    public String getSafeSortDirection() {
        return ("ASC".equalsIgnoreCase(sortDirection) || "DESC".equalsIgnoreCase(sortDirection))
                ? sortDirection.toUpperCase()
                : "DESC";
    }
}