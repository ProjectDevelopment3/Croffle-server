package com.sungshin.croffle.dto.stamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddStampRequestDto {
    private Long cafeId;

    @Builder
    public AddStampRequestDto(Long cafeId) {
        this.cafeId = cafeId;
    }
}
