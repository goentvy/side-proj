package com.entvy.openbidhub.controller;

import com.entvy.openbidhub.dto.ItemDetailDto;
import com.entvy.openbidhub.service.OnbidItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class OnbidItemController {

    private final OnbidItemService itemService;

    @GetMapping("/{cltrMnmtNo}")
    public ResponseEntity<ItemDetailDto> getItemDetail(@PathVariable String cltrMnmtNo) {
        ItemDetailDto dto = itemService.getItemDetail(cltrMnmtNo);
        return ResponseEntity.ok(dto);
    }
}