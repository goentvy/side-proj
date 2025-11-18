package com.entvy.openbidhub.mapper;

import com.entvy.openbidhub.dto.ItemDetailDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OnbidItemMapper {
    ItemDetailDto selectItemDetail(String cltrMnmtNo);
}
