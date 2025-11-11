package com.entvy.openbidhub.controller;

import com.entvy.openbidhub.dto.*;
import com.entvy.openbidhub.service.AuctionCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/onbid")
@RequiredArgsConstructor
public class OnbidItemQueryController {
    private final AuctionCardService auctionService;

    // auction_items Table 데이터 조회
    @GetMapping("/cards")
    public ResponseEntity<Page<AuctionCardDto>> getAuctionCards(Pageable pageable) {
        return ResponseEntity.ok(auctionService.getAuctionCards(pageable));
    }
}