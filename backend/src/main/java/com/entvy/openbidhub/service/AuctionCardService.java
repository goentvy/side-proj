package com.entvy.openbidhub.service;

import com.entvy.openbidhub.domain.AuctionItemEntity;
import com.entvy.openbidhub.dto.AuctionCardDto;
import com.entvy.openbidhub.mapper.AuctionCardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuctionCardService {

    private final AuctionCardMapper auctionCardMapper;

    // action_items 테이블 확인
    public List<AuctionCardDto> getAuctionCards() {
        return auctionCardMapper.findAuctionCards();
    }
}
