package com.entvy.openbidhub.mapper;

import com.entvy.openbidhub.dto.ApprovalDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApprovalMapper {
    List<ApprovalDto> selectPendingApprovals();
}