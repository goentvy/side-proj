package com.entvy.openbidhub.controller;

import com.entvy.openbidhub.domain.OnbidItemEntity;
import com.entvy.openbidhub.dto.*;
import com.entvy.openbidhub.service.AuctionCardService;
import com.entvy.openbidhub.service.OnbidItemQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/onbid")
@RequiredArgsConstructor
public class OnbidItemQueryController {

    private final OnbidItemQueryService queryService;
    private final AuctionCardService auctionService;

    @GetMapping("/items")
    public PageResponse<OnbidItemResponse> getAllItems(@PageableDefault(size = 10) Pageable pageable) {
        return queryService.getAll(pageable);
    }

    @PostMapping("/items/search")
    public PageResponse<OnbidItemResponse> searchItems(@RequestBody OnbidItemSearchCondition cond,
                                                       @PageableDefault(size = 10) Pageable pageable) {
        return queryService.search(cond, pageable);
    }

    @GetMapping("/items/latest")
    public ResponseEntity<List<OnbidItemDto>> getLatestItems() {
        List<OnbidItemDto> items = queryService.getLatestItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/cards")
    public ResponseEntity<List<AuctionCardDto>> getAuctionCards() {
        return ResponseEntity.ok(auctionService.getAuctionCards());
    }
}