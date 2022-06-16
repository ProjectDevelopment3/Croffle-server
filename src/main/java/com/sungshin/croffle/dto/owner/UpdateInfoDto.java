package com.sungshin.croffle.dto.owner;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
public class UpdateInfoDto {
    private String cafeName;
    private String telephone;
    private String hours;
    private String site;
    private String benefit;

    @Builder
    public UpdateInfoDto(String cafeName, String telephone, String hours, String site, String benefit){
        this.cafeName = cafeName;
        this.telephone = telephone;
        this.hours = hours;
        this.site =site;
        this.benefit =benefit;
    }
}
