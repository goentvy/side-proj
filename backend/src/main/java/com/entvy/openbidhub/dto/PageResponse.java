package com.entvy.openbidhub.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Builder
@Schema(description = "페이징 응답 DTO")
public class PageResponse<T> {

    @Schema(description = "현재 페이지의 데이터 목록")
    private List<T> content;

    @Schema(description = "현재 페이지 번호", example = "0")
    private int page;

    @Schema(description = "페이지당 데이터 개수", example = "10")
    private int size;

    @Schema(description = "전체 데이터 개수", example = "125")
    private long totalElements;

    @Schema(description = "전체 페이지 수", example = "13")
    private int totalPages;

    public static <T> PageResponse<T> from(org.springframework.data.domain.Page<T> page) {
        return PageResponse.<T>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }
}

