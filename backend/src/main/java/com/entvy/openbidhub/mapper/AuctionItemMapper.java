package com.entvy.openbidhub.mapper;

import com.entvy.openbidhub.domain.AuctionItemEntity;
import com.entvy.openbidhub.dto.RegionBidSummary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AuctionItemMapper {

    void insertItems(List<AuctionItemEntity> items);
    // 지역별 입찰 수량 집계
    List<RegionBidSummary> getRegionBidSummary();
    // 입찰 상태별 건수 집계
    List<Map<String, Object>> selectBidStatusSummary();
}
