package com.sungshin.croffle.dto.owner;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OwnerCheckRequestDto {
    private String cafeName;
    private String cafeAddr;
    private String b_no;
    private String start_dt;
    private String name;

    @Builder
    public OwnerCheckRequestDto(String cafeName, String cafeAddr, String b_no, String start_dt, String name) {
        this.cafeName = cafeName;
        this.cafeAddr = cafeAddr;
        this.b_no = b_no;
        this.start_dt = start_dt;
        this.name = name;
    }
}
