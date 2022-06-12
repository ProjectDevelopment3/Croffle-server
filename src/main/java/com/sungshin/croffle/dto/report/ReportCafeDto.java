package com.sungshin.croffle.dto.report;

import com.sungshin.croffle.domain.Cafe;
import com.sungshin.croffle.domain.Menu;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
public class ReportCafeDto {
    private String cafeName;
    private String roadAddress;
    private List<ReportMenuDto> menuList;


    public Cafe toEntity(){
        Cafe build = Cafe.builder()
                .name(cafeName)
                .addr(roadAddress)
                .build();
        return build;
    }


    @Builder
    public ReportCafeDto(String cafeName, String roadAddress, List<ReportMenuDto> menuList){
        this.cafeName = cafeName;
        this.roadAddress = roadAddress;
        this.menuList = menuList;
    }




}
