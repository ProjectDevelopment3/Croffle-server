package com.sungshin.croffle.controller;

import com.sungshin.croffle.config.auth.UserPrincipal;
import com.sungshin.croffle.dto.Response;
import com.sungshin.croffle.dto.stamp.AddStampRequestDto;
import com.sungshin.croffle.dto.stamp.StampListDto;
import com.sungshin.croffle.service.StampService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class StampController {

    private final StampService stampService;

    @GetMapping("/stamps")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Response<StampListDto> userStampList(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Long userId = userPrincipal.getId();
        return Response.<StampListDto>builder()
                .code("200")
                .messages("스탬프 리스트 조회에 성공하였습니다.")
                .data(stampService.stampList(userId))
                .build();
    }

    @PostMapping("/owner/stamp")
    public Response addStamp(@RequestBody AddStampRequestDto stampRequestDto,
                             Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        stampService.addStamp(stampRequestDto.getCafeId(), stampRequestDto.getUserId());
        return Response.builder()
                .code("201")
                .messages("스탬프 찍어 주기가 완료 되었습니다.")
                .build();
    }
}
