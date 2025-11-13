package com.entvy.openbidhub.controller;

import com.entvy.openbidhub.service.OnbidRawItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Tag(name = "Raw Items", description = "온비드 원시 데이터 처리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/raw-items")
public class OnbidRawItemController {

    private final OnbidRawItemService rawItemService;

    @Operation(
            summary = "원시 데이터 가져오기 및 저장",
            description = "온비드 원시 데이터를 외부에서 가져와 중복 제거 및 정제 후 DB에 저장합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "데이터 저장 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/import")
    public ResponseEntity<Map<String, Object>> importRawItems() {
        try {
            int count = rawItemService.saveRawItems();
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("message", "Raw items imported", "importedCount", count));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
