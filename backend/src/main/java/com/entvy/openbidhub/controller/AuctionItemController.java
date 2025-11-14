package com.entvy.openbidhub.controller;

import com.entvy.openbidhub.dto.AuctionCardDto;
import com.entvy.openbidhub.dto.OnbidItemSearchCondition;
import com.entvy.openbidhub.dto.RegionBidSummary;
import com.entvy.openbidhub.service.AuctionItemQueryService;
import com.entvy.openbidhub.service.AuctionItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Auction Items", description = "경매 물건 관련 API")
@Slf4j
@RestController
@RequestMapping("/api/auction-items")
public class AuctionItemController {

    private final AuctionItemService auctionItemService;
    private final AuctionItemQueryService auctionItemQueryService;

    public AuctionItemController(AuctionItemService auctionItemService,
                                 AuctionItemQueryService auctionItemQueryService) {
        this.auctionItemService = auctionItemService;
        this.auctionItemQueryService = auctionItemQueryService;
    }

    @Operation(summary = "경매 데이터 가공 및 저장", description = "원본 데이터를 중복 제거 및 정제하여 저장합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "데이터 저장 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/process")
    public ResponseEntity<Map<String, Object>> processItems() {
        try {
            int count = auctionItemService.processAndSave();
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("message", "Auction items processed", "savedCount", count));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @Operation(summary = "조건 검색", description = "입력된 조건에 따라 경매 물건을 페이징 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "검색 성공")
    })
    @GetMapping
    public ResponseEntity<Page<AuctionCardDto>> search(
            @Parameter(description = "검색 조건") @ModelAttribute OnbidItemSearchCondition cond,
            @Parameter(description = "페이징 정보") Pageable pageable
    ) {
        log.debug("검색 조건: {}", cond);
        return ResponseEntity.ok(auctionItemQueryService.search(cond, pageable));
    }

    @Operation(
            summary = "지역별 입찰 수량 조회",
            description = "auction_items 테이블의 cltr_nm 필드를 기준으로 지역명을 추출하여 입찰 수량을 집계합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "지역별 입찰 수량 목록",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RegionBidSummary.class)
                            )
                    )
            }
    )
    @GetMapping("/region-summary")
    public List<RegionBidSummary> getRegionBidSummary() {
        return auctionItemService.getRegionBidSummary();
    }

    @SecurityRequirement(name = "bearerAuth")
    @Operation(
            summary = "입찰 상태 요약 조회",
            description = "입찰준비중, 인터넷입찰진행중 상태별 입찰 건수를 반환합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "입찰 상태 요약 조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "403", description = "접근 권한 없음")
    })
    @GetMapping("/bid-status-summary")
    public ResponseEntity<Map<String, Long>> getBidStatusSummary() {
        return ResponseEntity.ok(auctionItemService.getBidStatusSummary());
    }
}
