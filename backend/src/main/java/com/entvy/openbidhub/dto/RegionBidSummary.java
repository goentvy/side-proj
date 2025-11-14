package com.entvy.openbidhub.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "지역별 입찰 수량 DTO")
public class RegionBidSummary {
    @Schema(description = "지역명", example = "서울")
    private String region;

    @Schema(description = "입찰 수량", example = "215")
    private int count;

    // Getters and Setters
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
