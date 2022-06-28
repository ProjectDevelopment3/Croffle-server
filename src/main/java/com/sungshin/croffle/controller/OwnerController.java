package com.sungshin.croffle.controller;

import com.sungshin.croffle.config.auth.UserPrincipal;
import com.sungshin.croffle.dto.Response;
import com.sungshin.croffle.dto.owner.*;
import com.sungshin.croffle.dto.user.StampRequestDto;
import com.sungshin.croffle.dto.user.StampUserInfoDto;
import com.sungshin.croffle.service.MenuService;
import com.sungshin.croffle.service.OwnerService;
import com.sungshin.croffle.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RestController
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerService ownerService;
    private final UserService userService;
    private final MenuService menuService;

    //사장님 서비스 가게 정보 조회
    @GetMapping("/owner/cafe")
//    @PreAuthorize("hasRole('ROLE_OWNER')")
    public Response getCafeInfo(Authentication authentication) {
        //session에 있는 userId를 넘겨주고
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Long cafeId = userService.findCafeId(userPrincipal.getId());
        SearchCafeInfoDto cafeInfo = ownerService.getCafeInfo(cafeId);
        return Response.builder()
                .code("200")
                .messages("매장 정보 조회에 성공했습니다.")
                .data(Collections.singletonList(cafeInfo))
                .build();

    }

    //사장님 서비스 가게 정보 수정
    @PutMapping("/owner/cafe/{id}")
//    @PreAuthorize("hasRole('ROLE_OWNER')")
    public Response updateInfo(@PathVariable Long cafeId, @RequestBody UpdateInfoDto updateInfoDto) {
        ownerService.updateInfo(cafeId, updateInfoDto);
        return Response.builder()
                .code("201")
                .messages("매장 정보 수정에 성공했습니다.")
                .build();
    }

    //사장님 서비스 메뉴 조회
    @GetMapping("/owner/menus")
//    @PreAuthorize("hasRole('ROLE_OWNER')")
    public Response<SearchMenuDto> getCafeMenu(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return Response.<SearchMenuDto>builder()
                .code("200")
                .messages("메뉴 조회에 성공하였습니다.")
                .data(menuService.getMenuList(userPrincipal.getId()))
                .build();
    }

    //사장님 서비스 메뉴 추가
    @PostMapping("/owner/menu")
//    @PreAuthorize("hasRole('ROLE_OWNER')")
    public Response addMenu(Authentication authentication, @RequestBody CreateMenuDto createMenuDto) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        menuService.addMenu(createMenuDto);
        return Response.builder()
                .code("201")
                .messages("메뉴 추가가 완료 되었습니다.")
                .build();
    }

    //사징님 서비스 메뉴 수정
    @PutMapping("/owner/menu/{id}")
//    @PreAuthorize("hasRole('ROLE_OWNER')")
    public Response updateMenu(Authentication authentication, @PathVariable Long id, @RequestBody UpdateMenuDto menuDto) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        menuService.updateMenu(id, userPrincipal.getId(), menuDto);
        return Response.builder()
                .code("201")
                .messages("메뉴 수정이 완료 되었습니다.")
                .data(Collections.singletonList(menuService.getMenu(id)))
                .build();
    }

//    @PreAuthorize("hasRole('ROLE_USER')")
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

    //사장님 서비스 회원 조회
    @PostMapping("/owner/find-user")
//    @PreAuthorize("hasRole('ROLE_OWNER')")
    public Response checkUser(@RequestBody StampRequestDto stampRequestDto) {
        if (ownerService.searchNum(stampRequestDto) < 0L) {
            return Response.builder()
                    .code("4000")
                    .messages("존재하지 않는 회원입니다.")
                    .build();
        }

        return Response.<StampUserInfoDto>builder()
                .code("200")
                .messages("스탬프 적립이 가능한 회원 입니다.")
                .data(Collections.singletonList(ownerService.getUserAndStampInfo(stampRequestDto)))
                .build();

    }

}
