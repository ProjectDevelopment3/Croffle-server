package com.sungshin.croffle.controller;

import com.sungshin.croffle.config.auth.UserPrincipal;
import com.sungshin.croffle.dto.Response;
import com.sungshin.croffle.dto.owner.*;
import com.sungshin.croffle.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @GetMapping("/owner/menu/{id}")
    public Response getCafeMenu(@PathVariable Long id){
        return Response.builder()
                .code("200")
                .messages("메뉴 조회에 성공하였습니다.")
                .data(Collections.singletonList(ownerService.getMenu(id)))
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

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/owner/verify")
    public Response checkedOwner(Authentication authentication, @RequestBody OwnerCheckRequestDto checkRequestDto) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        if (!ownerService.checkedOwner(checkRequestDto, userPrincipal.getId())) {
            return Response.builder()
                    .code("4010")
                    .messages("사장님이 아니거나, 유효한 사업자 등록증이 아닙니다.")
                    .build();
        }
        ownerService.ownerRoleAdd(userPrincipal.getId(), checkRequestDto);
        return Response.builder()
                .code("200")
                .messages("사장님 인증이 완료되었습니다.")
                .build();
    }

}
