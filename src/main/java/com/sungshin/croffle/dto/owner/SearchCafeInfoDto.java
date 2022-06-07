package com.sungshin.croffle.dto.owner;
import com.sungshin.croffle.domain.Cafe;
import lombok.Getter;

@Getter
public class SearchCafeInfoDto {

    private String cafeName;
    private String cafeAddr;
    private String cafeTelephone;
    private String cafeHours;
    private String cafeSite;
    private String cafeBenefit;


    public SearchCafeInfoDto(Cafe entity){
        this.cafeName = entity.getName();
        this.cafeAddr = entity.getAddr();
        this.cafeTelephone = entity.getTelephone();
        this.cafeHours = entity.getHours();
        this.cafeSite = entity.getSite();
        this.cafeBenefit = entity.getBenefit();
    }
}
