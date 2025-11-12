package com.entvy.openbidhub.mapper;

import com.entvy.openbidhub.domain.OnbidRawItemEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OnbidRawItemMapper {
    void insertAll(@Param("list") List<OnbidRawItemEntity> items);
    List<OnbidRawItemEntity> findAll();
}
