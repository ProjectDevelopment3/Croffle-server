package com.sungshin.croffle.dto.owner;

import com.sungshin.croffle.domain.Menu;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@NoArgsConstructor
public class CreateMenuDto {
    private Long cafeId;
    private String name;
    private String price;

    @Builder
    public CreateMenuDto(Long cafeId, String name, String price){
        this.cafeId = cafeId;
        this.name = name;
        this.price = price;
    }

    public Menu toEntity(){
        return Menu.builder()
                .cafe_id(cafeId)
                .name(name)
                .price(price)
                .build();
    }

}
