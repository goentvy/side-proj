package com.entvy.openbidhub.controller;

import com.entvy.openbidhub.domain.OnbidItemEntity;
import com.entvy.openbidhub.dto.OnbidItemResponse;
import com.entvy.openbidhub.dto.OnbidItemSearchCondition;
import com.entvy.openbidhub.dto.PageResponse;
import com.entvy.openbidhub.service.OnbidItemQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/onbid")
@RequiredArgsConstructor
public class OnbidItemQueryController {

    private final OnbidItemQueryService queryService;

    @GetMapping("/items")
    public PageResponse<OnbidItemResponse> getAllItems(@PageableDefault(size = 10) Pageable pageable) {
        return queryService.getAll(pageable);
    }

    @PostMapping("/items/search")
    public PageResponse<OnbidItemResponse> searchItems(@RequestBody OnbidItemSearchCondition cond,
                                                       @PageableDefault(size = 10) Pageable pageable) {
        return queryService.search(cond, pageable);
    }
}