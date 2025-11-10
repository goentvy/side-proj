package com.entvy.openbidhub.mapper;

import com.entvy.openbidhub.domain.AuctionItemEntity;
import com.entvy.openbidhub.dto.AuctionCardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuctionCardMapper {
    List<AuctionCardDto> findAuctionCards();
}
