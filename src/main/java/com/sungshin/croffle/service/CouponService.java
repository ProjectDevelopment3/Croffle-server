package com.sungshin.croffle.service;

import com.sungshin.croffle.domain.jpa.CouponRepository;
import com.sungshin.croffle.dto.coupon.CouponListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;

    public List<CouponListResponseDto> couponList(Long userId) {
        return couponRepository.findCouponList(userId);
    }
}
