package com.sungshin.croffle.service;

import com.sungshin.croffle.config.ApiKey;
import com.sungshin.croffle.domain.Cafe;
import com.sungshin.croffle.domain.Menu;
import com.sungshin.croffle.domain.jpa.CafeRepository;
import com.sungshin.croffle.domain.jpa.MenuRepository;
import com.sungshin.croffle.domain.jpa.UserRepository;
import com.sungshin.croffle.domain.user.Role;
import com.sungshin.croffle.domain.user.User;
import com.sungshin.croffle.dto.owner.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import okhttp3.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class OwnerService {
    private final CafeRepository cafeRepository;
    private final MenuRepository menuRepository;
    private final UserRepository userRepository;
    private final ApiKey apiKey;

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

    @Transactional(readOnly = true)
    public boolean checkedOwner(OwnerCheckRequestDto checkRequestDto, Long userId) {
        String userName = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 User id 입니다."))
                .getName();
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        // json body
        MediaType mediaType = MediaType.parse("application/json");
        List<Map<String, Object>> jsonObjects = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("b_no", checkRequestDto.getB_no());
        map.put("start_dt", checkRequestDto.getStart_dt());
        map.put("p_nm", checkRequestDto.getName());
        map.put("b_nm", checkRequestDto.getCafeName());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("businesses", jsonObjects);
        RequestBody body = RequestBody.create(jsonObject.toString(), mediaType);

        Request request = new Request.Builder()
                .url("http://api.odcloud.kr/api/nts-businessman/v1/validate?serviceKey=" + apiKey.getServiceKey())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            String res = response.body().toString();
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(res);
            JSONObject jsonObj = (JSONObject) obj;
            log.info("요청 결과: " + jsonObj);
            if (jsonObj.get("status_code").equals("OK")) {
                if (userName.equals(checkRequestDto.getName())) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return false;
    }

    @Transactional
    public void ownerRoleAdd(Long userId, OwnerCheckRequestDto checkRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 User id 입니다."));
        Long cafeId = cafeRepository.findByAddr(checkRequestDto.getCafeAddr())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카페 이름입니다.")).getId();
        //user role change
        user.updateOwner(Role.OWNER, cafeId);
    }

}
