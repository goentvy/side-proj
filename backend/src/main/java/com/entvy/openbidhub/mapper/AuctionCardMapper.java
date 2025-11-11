package com.entvy.openbidhub.mapper;

import com.entvy.openbidhub.dto.AuctionCardDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AuctionCardMapper {
    List<AuctionCardDto> findAuctionCards(@Param("size") int size, @Param("offset") int offset);
    long countAuctionCards(); // 전체 개수 조회용
}
