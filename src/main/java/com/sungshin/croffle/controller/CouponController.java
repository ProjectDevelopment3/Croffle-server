package com.sungshin.croffle.controller;

import com.sungshin.croffle.config.auth.UserPrincipal;
import com.sungshin.croffle.dto.Response;
import com.sungshin.croffle.dto.coupon.CouponListResponseDto;
import com.sungshin.croffle.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @GetMapping("/coupons")
    public Response<CouponListResponseDto> couponList(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return Response.<CouponListResponseDto>builder()
                .code("200")
                .messages("쿠폰 리스트 조회에 성공하였습니다.")
                .data(couponService.couponList(userPrincipal.getId()))
                .build();
    }

    @DeleteMapping("/coupon/use")
    public Response couponUse(Authentication authentication, @RequestParam Long couponId) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        if (!couponService.useCoupon(userPrincipal.getId(), couponId)) {
            return Response.builder()
                    .code("400")
                    .messages("존재하지 않는 쿠폰이거나, 사용자의 쿠폰이 아닙니다.")
                    .build();
        }
        return Response.builder()
                .code("200")
                .messages("쿠폰 사용이 완료되었습니다.")
                .build();
    }

}
