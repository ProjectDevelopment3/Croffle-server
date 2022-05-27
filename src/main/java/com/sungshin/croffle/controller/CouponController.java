package com.sungshin.croffle.controller;

import com.sungshin.croffle.config.auth.UserPrincipal;
import com.sungshin.croffle.config.auth.dto.CurrentUser;
import com.sungshin.croffle.dto.Response;
import com.sungshin.croffle.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @GetMapping("/coupons")
    @PreAuthorize("hasRole('USER')")
    public Response couponList(@CurrentUser UserPrincipal userPrincipal) {
        return Response.builder()
                .code("200")
                .messages("쿠폰 리스트 조회에 성공하였습니다.")
                .data(Collections.singletonList(couponService.couponList(userPrincipal.getId())))
                .build();
    }
}