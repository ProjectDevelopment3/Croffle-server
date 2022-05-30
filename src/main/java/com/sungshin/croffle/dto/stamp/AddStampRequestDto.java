package com.sungshin.croffle.dto.stamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddStampRequestDto {
    private Long cafeId;
    private Long userId;

    @Builder
    public AddStampRequestDto(Long cafeId, Long userId) {
        this.cafeId = cafeId;
        this.userId = userId;
    }
}
