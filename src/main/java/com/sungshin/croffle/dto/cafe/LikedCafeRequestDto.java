package com.sungshin.croffle.dto.cafe;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikedCafeRequestDto {
    private Long cafe;

    public LikedCafeRequestDto(Long cafe) {
        this.cafe = cafe;
    }
}
