package com.sungshin.croffle.dto.report;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sungshin.croffle.domain.Menu;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class ReportMenuDto implements Serializable {
    @JsonIgnore
    private Long cafeId;
    private String name;
    private String price;

    public Menu toEntity(){
        return Menu.builder()
                .cafe_id(cafeId)
                .name(name)
                .price(price)
                .build();
    }

    public void setCafeId(Long cafeId) {
        this.cafeId = cafeId;
    }

    @Builder
    public ReportMenuDto (Long cafeId, String name, String price){
        this.cafeId = cafeId;
        this.name =name;
        this.price = price;
    }

}