package com.sungshin.croffle.dto.cafe;

import lombok.Getter;

@Getter
public class LikedCafeAddRequestDto {
    private Long cafe;

    public LikedCafeAddRequestDto(Long cafe) {
        this.cafe = cafe;
    }
}
