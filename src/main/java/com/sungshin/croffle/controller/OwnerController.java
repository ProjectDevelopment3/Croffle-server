package com.sungshin.croffle.controller;

import com.sungshin.croffle.config.auth.UserPrincipal;
import com.sungshin.croffle.dto.Response;
import com.sungshin.croffle.dto.owner.CreateMenuDto;
import com.sungshin.croffle.dto.owner.SearchCafeInfoDto;
import com.sungshin.croffle.dto.owner.UpdateInfoDto;
import com.sungshin.croffle.dto.owner.UpdateMenuDto;
import com.sungshin.croffle.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RestController
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerService ownerService;

    //사장님 서비스 가게 정보 조회
    @GetMapping("/owner/cafe")
    public Response getCafeInfo(@PathVariable Long cafe_id) {
        //session에 있는 userId를 넘겨주고
        SearchCafeInfoDto cafeInfo = ownerService.getCafeInfo(cafe_id);
        return Response.builder()
                .code("200")
                .messages("매장 정보 조회에 성공했습니다.")
                .data(Collections.singletonList(cafeInfo))
                .build();

    }

    //사장님 서비스 가게 정보 수정
    @PutMapping("/owner/cafe/{id}")
    public Response updateInfo(@PathVariable Long cafeId, UpdateInfoDto updateInfoDto) {
        ownerService.updateInfo(cafeId, updateInfoDto);
        return Response.builder()
                .code("201")
                .messages("매장 정보 수정에 성공했습니다.")
                .build();
    }


    //사장님 서비스 메뉴 개별 조회
    @GetMapping("/owner/menus")
    public Response getCafeMenu(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return Response.builder()
                .code("200")
                .messages("메뉴 조회에 성공하였습니다.")
                .data(ownerService.getMenuList(userPrincipal.getId()))
                .build();
    }


    //사장님 서비스 메뉴 추가
    @PostMapping("/owner/menu")
    public Response addMenu(Authentication authentication,@RequestBody CreateMenuDto createMenuDto) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        ownerService.addMenu(createMenuDto);
        return Response.builder()
                .code("201")
                .messages("메뉴 추가가 완료 되었습니다.")
                .build();
    }

    //사징님 서비스 메뉴 수정
    @PutMapping("/owner/menu/{id}")
    public Response updateMenu(Authentication authentication, @PathVariable Long id, @RequestBody UpdateMenuDto menuDto) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        ownerService.updateMenu(id,userPrincipal.getId(), menuDto);
        return Response.builder()
                .code("201")
                .messages("메뉴 수정이 완료 되었습니다.")
                .data(Collections.singletonList(ownerService.getMenu(id)))
                .build();
    }


}
