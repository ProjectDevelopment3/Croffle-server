package com.sungshin.croffle.dto;

import com.sungshin.croffle.domain.Menu;
import lombok.Getter;

@Getter
public class MenuListDto {
    private Long menuId;
    private Long cafeId;
    private String name;
    private String price;

    public MenuListDto(Menu menu) {
        this.menuId = menu.getId();
        this.cafeId = menu.getCafeId();
        this.name = menu.getName();
        this.price = menu.getPrice();
    }
}
