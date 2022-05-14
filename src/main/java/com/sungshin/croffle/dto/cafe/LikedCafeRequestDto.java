package com.sungshin.croffle.dto.cafe;

import lombok.Getter;

@Getter
public class LikedCafeRequestDto {
    private Long cafe;

    public LikedCafeRequestDto(Long cafe) {
        this.cafe = cafe;
    }
}
