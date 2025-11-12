package com.entvy.openbidhub.controller;

import com.entvy.openbidhub.dto.*;
import com.entvy.openbidhub.service.AuctionCardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Onbid Cards", description = "온비드 경매 카드 데이터 조회 API")
@RestController
@RequestMapping("/api/onbid")
@RequiredArgsConstructor
public class OnbidItemQueryController {

    private final AuctionCardService auctionService;

    @Operation(
            summary = "전체 경매 카드 조회",
            description = "auction_items 테이블에서 전체 경매 카드 데이터를 페이징 조회합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "403", description = "접근 권한 없음")
    })
    @GetMapping("/cards")
    public ResponseEntity<Page<AuctionCardDto>> getAuctionCards(
            @Parameter(description = "페이징 정보 (page, size, sort)") Pageable pageable
    ) {
        return ResponseEntity.ok(auctionService.getAuctionCards(pageable));
    }
}