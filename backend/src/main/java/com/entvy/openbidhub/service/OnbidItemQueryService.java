package com.entvy.openbidhub.service;

import com.entvy.openbidhub.dto.*;
import com.entvy.openbidhub.mapper.AuctionCardMapper;
import com.entvy.openbidhub.mapper.OnbidItemMapper;
import com.entvy.openbidhub.repository.OnbidItemRepository;
import com.entvy.openbidhub.repository.spec.OnbidItemSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OnbidItemQueryService {

    private final OnbidItemRepository repository;
    private final OnbidItemMapper onbidItemMapper;

    // 전체 조회 (페이징 + DTO 변환)
    public PageResponse<OnbidItemResponse> getAll(Pageable pageable) {
        Page<OnbidItemResponse> page = repository.findAll(pageable)
                .map(OnbidItemResponse::from);
        return PageResponse.from(page);
    }

    // 조건 검색 (Specification + DTO 변환)
    public PageResponse<OnbidItemResponse> search(OnbidItemSearchCondition cond, Pageable pageable) {
        Page<OnbidItemResponse> page = repository.findAll(OnbidItemSpecification.withCondition(cond), pageable)
                .map(OnbidItemResponse::from);
        return PageResponse.from(page);
    }

    // Mybatis
    public List<OnbidItemDto> getLatestItems() {
        return onbidItemMapper.findLatestItems();
    }
}