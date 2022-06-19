package com.sungshin.croffle.dto.report;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sungshin.croffle.domain.Cafe;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Getter
@NoArgsConstructor
public class ReportCafeDto implements Serializable {
    private String cafeName;
    private String roadAddress;
    private ReportMenuDto menuList;


    public Cafe toEntity(){
        Cafe build = Cafe.builder()
                .name(cafeName)
                .addr(roadAddress)
                .build();
        return build;
    }

    @Builder
    public ReportCafeDto(String cafeName, String roadAddress, ReportMenuDto menuList){
        this.cafeName = cafeName;
        this.roadAddress = roadAddress;
        this.menuList = menuList;
    }

}