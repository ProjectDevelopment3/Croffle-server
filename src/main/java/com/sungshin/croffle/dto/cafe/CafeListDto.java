package com.sungshin.croffle.dto.cafe;

import com.sungshin.croffle.domain.Cafe;
import lombok.Getter;

@Getter
public class CafeListDto {
    private Long id;
    private String name;
    private String addr;
    private String coords;

    public CafeListDto(Cafe cafe) {
        this.id = cafe.getId();
        this.name = cafe.getName();
        this.addr = cafe.getAddr();
        this.coords = cafe.getCoords();
    }
}
