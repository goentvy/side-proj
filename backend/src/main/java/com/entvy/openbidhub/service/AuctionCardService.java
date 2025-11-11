package com.entvy.openbidhub.service;

import com.entvy.openbidhub.dto.AuctionCardDto;
import com.entvy.openbidhub.mapper.AuctionCardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuctionCardService {

    private final AuctionCardMapper auctionCardMapper;

    // action_items 테이블 확인
    public Page<AuctionCardDto> getAuctionCards(Pageable pageable) {
        int size = pageable.getPageSize();
        int offset = pageable.getPageNumber() * size;

        List<AuctionCardDto> items = auctionCardMapper.findAuctionCards(size, offset);
        long total = auctionCardMapper.countAuctionCards();
        return new PageImpl<>(items, pageable, total);
    }
}
