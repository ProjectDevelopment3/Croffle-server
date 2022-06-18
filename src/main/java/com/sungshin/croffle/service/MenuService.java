package com.sungshin.croffle.service;

import com.sungshin.croffle.domain.Menu;
import com.sungshin.croffle.domain.jpa.MenuRepository;
import com.sungshin.croffle.domain.jpa.UserRepository;
import com.sungshin.croffle.dto.MenuListDto;
import com.sungshin.croffle.dto.owner.CreateMenuDto;
import com.sungshin.croffle.dto.owner.SearchMenuDto;
import com.sungshin.croffle.dto.owner.UpdateMenuDto;
import com.sungshin.croffle.dto.report.ReportCafeDto;
import com.sungshin.croffle.dto.report.ReportMenuDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final UserRepository userRepository;


    /**
     * 사장님 서비스 -메뉴 관련 기능
     **/

    @Transactional(readOnly = true)
    public List<SearchMenuDto> getMenuList(Long userId) {
        Long cafeId = userRepository.findById(userId).orElseThrow().getOwner();
        List<SearchMenuDto> menuList = new ArrayList<SearchMenuDto>();
        List<Menu> menus = menuRepository.findAllByCafeIdAndAndChecked(cafeId, true);
        for (int i = 0; i < menus.size(); i++) {
            Menu menu = (menus.get(i));
            menuList.add(new SearchMenuDto(menu));
        }
        return menuList;
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

    @Transactional(readOnly = true)
    public List<MenuListDto> getCheckedMenuList(Long cafeId) {
        return menuRepository.findAllByCafeIdAndAndChecked(cafeId, true)
                .stream().map(MenuListDto::new).collect(Collectors.toList());
    }


    @Transactional
    public Long updateMenu(Long id, Long userid, UpdateMenuDto updateMenuDto) {
        Menu menu = menuRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 메뉴가 없습니다. id=" + id));
        menu.update(updateMenuDto.getMenuName(), updateMenuDto.getMenuPrice());
        return id;
    }

    /**
     * 제보 기능 중 메뉴 제보
     **/

    @Transactional(readOnly = true)
    public boolean searchMenu(ReportCafeDto reportCafeDto) {
        if (reportCafeDto.getMenuList().get(0) != null) {
            return true;
        }
        return false;
    }

    @Transactional
    public Long saveMenu(ReportCafeDto reportCafeDto, Long cafe_id) {
        for (int i = 0; i < reportCafeDto.getMenuList().size(); i++) {
            ReportMenuDto reportMenuDto = reportCafeDto.getMenuList().get(i);
            reportMenuDto.setCafeId(cafe_id);
            menuRepository.save(reportMenuDto.toEntity());
        }
        return cafe_id;
    }


}
