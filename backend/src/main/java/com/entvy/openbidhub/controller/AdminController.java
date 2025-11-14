package com.entvy.openbidhub.controller;

import com.entvy.openbidhub.dto.ApprovalDto;
import com.entvy.openbidhub.service.ApprovalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@Tag(name = "Admin", description = "관리자 전용 API")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class AdminController {

    private final ApprovalService approvalService;

    @Operation(summary = "승인 대기 입찰 목록 조회")
    @GetMapping("/approval-list")
    public ResponseEntity<List<ApprovalDto>> getApprovalList() {
        return ResponseEntity.ok(approvalService.getPendingApprovals());
    }
}