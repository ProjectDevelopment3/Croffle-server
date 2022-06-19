package com.sungshin.croffle.controller;

import com.sungshin.croffle.config.auth.UserPrincipal;
import com.sungshin.croffle.dto.Response;
import com.sungshin.croffle.dto.user.NickNameRequestDto;
import com.sungshin.croffle.dto.user.UserDto;
import com.sungshin.croffle.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Response<UserDto> getCurrentUser(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return Response.<UserDto>builder()
                .code("200")
                .messages("사용자 조회가 완료되었습니다.")
                .data(Collections.singletonList(userService.findById(userPrincipal.getId())))
                .build();
    }

    @PostMapping("/nickname/verify")
    @PermitAll
    public Response nicknameCheck(@RequestBody NickNameRequestDto nicknameDto) {
        if (userService.nicknameVerify(nicknameDto.getNickname())) {
            return Response.builder()
                    .code("200")
                    .messages("사용 가능한 닉네임입니다.")
                    .build();
        } else {
            return Response.builder()
                    .code("4000")
                    .messages("사용 불가능한 닉네입니다.")
                    .build();
        }
    }

    @PutMapping("/nickname")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Response nicknameEdit(Authentication authentication, @RequestBody NickNameRequestDto nicknameDto) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String changedNickname = userService.nicknameEdit(userPrincipal.getId(), nicknameDto.getNickname()).getNickname();
        if (nicknameDto.getNickname().equals(changedNickname))
            return Response.builder()
                    .code("200")
                    .messages("닉네임 변경에 성공하였습니다.")
                    .build();
        return Response.builder()
                .code("4000")
                .messages("닉네임 변경에 실패하였습니다.")
                .build();
    }
}
