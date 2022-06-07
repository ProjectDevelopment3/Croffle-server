package com.sungshin.croffle.service;

import com.sungshin.croffle.domain.Cafe;
import com.sungshin.croffle.domain.Menu;
import com.sungshin.croffle.domain.jpa.CafeRepository;
import com.sungshin.croffle.domain.jpa.MenuRepository;
import com.sungshin.croffle.dto.owner.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService {
    private final CafeRepository cafeRepository;
    private final MenuRepository menuRepository;


    @Transactional(readOnly = true)
    public SearchCafeInfoDto getCafeInfo(Long cafe_id) {
        Cafe cafeInfo = cafeRepository.findById(cafe_id).orElseThrow(() -> new IllegalArgumentException("해당 카페가 없습니다. id = " + cafe_id));
        SearchCafeInfoDto info = new SearchCafeInfoDto(cafeInfo);
        return info;
    }


    @Transactional(readOnly = true)
    public List<SearchMenuDto> getMenuList(Long cafe_id) {
        List<SearchMenuDto> menuList = new ArrayList<SearchMenuDto>();
        List<Menu> menus = menuRepository.findByCafeId(cafe_id);
        for (int i = 0; i <menus.size(); i++) {
            Menu menu = (menus.get(i));
            menuList.add(new SearchMenuDto(menu));
        }
        return menuList;
    }




    @Transactional
    public Long updateInfo(Long id, UpdateInfoDto updateInfoDto) {
        Cafe cafe = cafeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 카페가 없습니다. id = " + id));
        cafe.update(updateInfoDto.getCafeName(), updateInfoDto.getTelephone(), updateInfoDto.getHours(), updateInfoDto.getSite(), updateInfoDto.getBenefit());
        return id;
    }



    @Transactional
    public Long addMenu(CreateMenuDto createMenuDto) {
        return menuRepository.save(createMenuDto.toEntity()).getId();
    }


    @Transactional(readOnly = true)
    public SearchMenuDto getMenu(Long id) {
        Menu menu = menuRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 메뉴 입니다. id = " + id));
        SearchMenuDto searchMenuDto = new SearchMenuDto(menu);
        return searchMenuDto;
    }


    @Transactional
    public Long updateMenu(Long id, Long userid , UpdateMenuDto updateMenuDto) {
        Menu menu = menuRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 메뉴가 없습니다. id=" + id));
        menu.update(updateMenuDto.getMenuName(), updateMenuDto.getMenuPrice());
        return id;
    }



}
