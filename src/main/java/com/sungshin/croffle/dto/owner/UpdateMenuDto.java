package com.sungshin.croffle.dto.owner;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateMenuDto {
    private String menuName;
    private String menuPrice;

    @Builder
    public UpdateMenuDto(String menuName, String menuPrice){
        this.menuName = menuName;
        this.menuPrice = menuPrice;
    }
}
