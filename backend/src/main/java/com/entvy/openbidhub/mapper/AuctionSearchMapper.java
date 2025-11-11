package com.entvy.openbidhub.mapper;

import com.entvy.openbidhub.dto.AuctionCardDto;
import com.entvy.openbidhub.dto.OnbidItemSearchCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AuctionSearchMapper {
    List<AuctionCardDto> searchByCondition(
            @Param("cond") OnbidItemSearchCondition cond,
            @Param("size") int size,
            @Param("offset") int offset
    );

    long countByCondition(@Param("cond") OnbidItemSearchCondition cond);
}
