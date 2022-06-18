package com.sungshin.croffle.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class StampRequestDto {
    private String telephone;
    private Long cafeId;

    @Builder
    public StampRequestDto(String telephone, Long cafeId){
        this.telephone = telephone;
        this.cafeId = cafeId;
    }

}
