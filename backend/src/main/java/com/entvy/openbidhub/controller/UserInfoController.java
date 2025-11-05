package com.entvy.openbidhub.controller;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserInfoController {

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");

        if(claims == null) {
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
