package com.sungshin.croffle.dto.cafe;

import com.sungshin.croffle.domain.Cafe;
import com.sungshin.croffle.dto.MenuListDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CafeDetailDto {
    private Long id;
    private String name;
    private String roadaddr;
    private String telephone;
    private String hours;
    private String site;
    private List<MenuListDto> menuListDtos;

    public CafeDetailDto(Cafe cafe) {
        this.id = cafe.getId();
        this.name = cafe.getName();
        this.roadaddr = cafe.getAddr();
        this.telephone = cafe.getTelephone();
        this.hours = cafe.getHours();
        this.site = cafe.getSite();
    }

    public void setMenuListDtos(List<MenuListDto> menuListDtos) {
        this.menuListDtos = menuListDtos;
    }
}
