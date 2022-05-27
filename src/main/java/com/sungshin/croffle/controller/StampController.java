package com.sungshin.croffle.controller;

import com.sungshin.croffle.config.auth.UserPrincipal;
import com.sungshin.croffle.config.auth.dto.CurrentUser;
import com.sungshin.croffle.dto.Response;
import com.sungshin.croffle.service.StampService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class StampController {

    private final StampService stampService;

    @GetMapping("/stamps")
    public Response userStampList(@CurrentUser UserPrincipal userPrincipal) {
        Long userId = userPrincipal.getId();
        return Response.builder()
                .code("200")
                .messages("스탬프 리스트 조회에 성공하였습니다.")
                .data(Collections.singletonList(stampService.stampList(userId)))
                .build();
    }
}
