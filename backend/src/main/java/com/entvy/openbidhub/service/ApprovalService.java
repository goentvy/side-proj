package com.entvy.openbidhub.service;

import com.entvy.openbidhub.dto.ApprovalDto;
import com.entvy.openbidhub.mapper.ApprovalMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApprovalService {

    private final ApprovalMapper approvalMapper;

    public List<ApprovalDto> getPendingApprovals() {
        return approvalMapper.selectPendingApprovals();
    }
}