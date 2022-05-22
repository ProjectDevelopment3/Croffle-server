package com.sungshin.croffle.dto.report;

import com.sungshin.croffle.domain.Cafe;
import com.sungshin.croffle.domain.Menu;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class ReportCafeDto {
    private String cafeName;
    private String coords;
    private String roadAddress;
    private List<ReportMenuDto> menuList;


    public Cafe toEntity(){
        Cafe build = Cafe.builder()
                .name(cafeName)
                .coords(coords)
                .addr(roadAddress)
                .build();
        return build;
    }


    @Builder
    public ReportCafeDto(String cafeName, String coords, String roadAddress, List<ReportMenuDto> menuList){
        this.cafeName = cafeName;
        this.coords = coords;
        this.roadAddress = roadAddress;
        this.menuList = menuList;
    }




}
