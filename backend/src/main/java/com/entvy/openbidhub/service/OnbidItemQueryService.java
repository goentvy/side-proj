package com.entvy.openbidhub.service;

import com.entvy.openbidhub.domain.OnbidItemEntity;
import com.entvy.openbidhub.dto.OnbidItemResponse;
import com.entvy.openbidhub.dto.OnbidItemSearchCondition;
import com.entvy.openbidhub.dto.PageResponse;
import com.entvy.openbidhub.repository.OnbidItemRepository;
import com.entvy.openbidhub.repository.spec.OnbidItemSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OnbidItemQueryService {

    private final OnbidItemRepository repository;

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
}