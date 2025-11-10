package com.entvy.openbidhub.mapper;

import com.entvy.openbidhub.dto.OnbidItemDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OnbidItemMapper {
    List<OnbidItemDto> findLatestItems();
}
