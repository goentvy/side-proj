package com.entvy.openbidhub.controller;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Tag(name = "User Info", description = "현재 로그인된 사용자 정보 조회 API")
@RestController
@RequestMapping("/api")
public class UserInfoController {

    @Operation(summary = "현재 사용자 정보 조회", description = "JWT 토큰을 기반으로 현재 로그인된 사용자의 이메일과 역할을 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증 정보 없음")
    })
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");

        if (claims == null) {
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED)
                    .body("인증 정보 없음");
        }
        String email = claims.getSubject();
        String role = claims.get("role", String.class);

        return ResponseEntity.ok(Map.of(
                "email", email,
                "role", role
        ));
    }
}
