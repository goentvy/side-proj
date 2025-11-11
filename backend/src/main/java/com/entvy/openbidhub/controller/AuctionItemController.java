package com.entvy.openbidhub.controller;

import com.entvy.openbidhub.dto.AuctionCardDto;
import com.entvy.openbidhub.dto.OnbidItemSearchCondition;
import com.entvy.openbidhub.service.AuctionItemQueryService;
import com.entvy.openbidhub.service.AuctionItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    // 조건에 맞는 데이터 조회
    @GetMapping
    public ResponseEntity<Page<AuctionCardDto>> search(
            @ModelAttribute OnbidItemSearchCondition cond,
            Pageable pageable
    ) {
        log.debug("검색 조건: {}", cond);
        return ResponseEntity.ok(auctionItemQueryService.search(cond, pageable));
    }

    // 공공API 전체데이터 중복 제거 및 데이터 DB 저장
    @PostMapping("/import")
    public ResponseEntity<String> importAuctionItems() {
        auctionItemService.saveAuctionItems();
        return ResponseEntity.status(HttpStatus.CREATED).body("Auction items imported successfully.");
    }
}
