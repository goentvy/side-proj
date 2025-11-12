package com.entvy.openbidhub.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Tag(name = "Protected Access", description = "권한 기반 접근 제어 API")
@RestController
@RequestMapping("/api")
public class ProtectedController {

    @Operation(summary = "ADMIN 전용 접근", description = "ADMIN 권한이 있어야 접근 가능한 API입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "접근 성공"),
            @ApiResponse(responseCode = "403", description = "접근 권한 없음")
    })
    @GetMapping("/admin")
    public ResponseEntity<String> adminAccess() {
        return ResponseEntity.ok("ADMIN 전용 API 접근 성공");
    }

    @Operation(summary = "USER 또는 ADMIN 접근", description = "USER 또는 ADMIN 권한이 있어야 접근 가능한 API입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "접근 성공"),
            @ApiResponse(responseCode = "403", description = "접근 권한 없음")
    })
    @GetMapping("/user")
    public ResponseEntity<String> userAccess() {
        return ResponseEntity.ok("USER 또는 ADMIN 접근 성공");
    }
}