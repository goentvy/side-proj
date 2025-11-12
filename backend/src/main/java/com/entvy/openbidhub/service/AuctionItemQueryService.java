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

    public Page<AuctionCardDto> search(OnbidItemSearchCondition cond, Pageable pageable) {
        int size = pageable.getPageSize();
        int offset = calculateOffset(pageable);

        List<AuctionCardDto> results = auctionSearchMapper.searchByCondition(cond, size, offset);
        long totalCount = auctionSearchMapper.countByCondition(cond);

        return new PageImpl<>(results, pageable, totalCount);
    }

    private int calculateOffset(Pageable pageable) {
        return pageable.getPageNumber() * pageable.getPageSize();
    }
}

