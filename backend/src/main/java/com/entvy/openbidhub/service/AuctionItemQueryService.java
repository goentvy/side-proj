package com.entvy.openbidhub.service;

import com.entvy.openbidhub.dto.AuctionCardDto;
import com.entvy.openbidhub.dto.OnbidItemSearchCondition;
import com.entvy.openbidhub.mapper.AuctionItemMapper;
import com.entvy.openbidhub.mapper.AuctionSearchMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuctionItemQueryService {

    private final AuctionSearchMapper auctionSearchMapper;
    private final AuctionItemMapper auctionItemMapper;

    public Page<AuctionCardDto> search(OnbidItemSearchCondition cond, Pageable pageable) {
        int size = pageable.getPageSize();
        int offset = pageable.getPageNumber() * size;

        List<AuctionCardDto> items = auctionSearchMapper.searchByCondition(cond, size, offset);
        long total = auctionSearchMapper.countByCondition(cond);
        return new PageImpl<>(items, pageable, total);
    }
}

