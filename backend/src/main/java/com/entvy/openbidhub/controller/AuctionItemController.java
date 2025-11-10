package com.entvy.openbidhub.controller;

import com.entvy.openbidhub.service.AuctionItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auction-items")
public class AuctionItemController {
    private final AuctionItemService auctionItemService;

    public AuctionItemController(AuctionItemService auctionItemService) {
        this.auctionItemService = auctionItemService;
    }

    @PostMapping("/import")
    public ResponseEntity<String> importAuctionItems() {
        auctionItemService.saveAuctionItems();
        return ResponseEntity.ok("Auction items imported successfully.");
    }
}
