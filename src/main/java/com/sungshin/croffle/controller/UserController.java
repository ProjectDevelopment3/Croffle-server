package com.sungshin.croffle.controller;

import com.sungshin.croffle.dto.Response;
import com.sungshin.croffle.dto.user.NickNameVerifyRequestDto;
import com.sungshin.croffle.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/nickname/verify")
    public Response nicknameCheck(@RequestBody NickNameVerifyRequestDto nicknameDto) {
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
}
