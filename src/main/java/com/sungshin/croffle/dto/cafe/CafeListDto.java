package com.sungshin.croffle.dto.cafe;

import com.sungshin.croffle.domain.Cafe;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class CafeListDto {
    private Long id;
    private String name;
    private String addr;

    public CafeListDto(Cafe cafe) {
        this.id = cafe.getId();
        this.name = cafe.getName();
        this.addr = cafe.getAddr();
    }
}
