package com.sungshin.croffle.dto.cafe;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CafeRecommendDto {
    private Long id;
    private String name;
    private String roadaddr;
    private double rate;
    private int liked_count;

    @Builder
    public CafeRecommendDto(Long id, String name, String roadaddr, double rate, int liked_count) {
        this.id = id;
        this.name = name;
        this.roadaddr = roadaddr;
        this.rate = rate;
        this.liked_count = liked_count;
    }
}
