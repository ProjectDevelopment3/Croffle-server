package com.sungshin.croffle.dto.report;

import com.sungshin.croffle.domain.Menu;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportMenuDto {

    private String name;
    private String price;

    public Menu toEntity(){
        return Menu.builder()
                .name(name)
                .price(price)
                .build();
    }

    @Builder
    public ReportMenuDto (String name, String price){
        this.name =name;
        this.price = price;
    }

}
