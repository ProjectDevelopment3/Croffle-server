package com.sungshin.croffle.dto.owner;

import com.sungshin.croffle.domain.Menu;
import lombok.Getter;

@Getter
public class SearchMenuDto {
    private Long menuId;
    private String menuName;
    private String menuPrice;
    private int checked;

   public SearchMenuDto(Menu entity){
       this.menuId = entity.getId();
       this.menuName = entity.getName();
       this.menuPrice = entity.getPrice();
       this.checked = entity.getChecked();
   }

}
