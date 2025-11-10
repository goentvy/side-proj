package com.entvy.openbidhub.mapper;

import com.entvy.openbidhub.domain.AuctionItemEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuctionItemMapper {
    void insertItems(List<AuctionItemEntity> items);
}
