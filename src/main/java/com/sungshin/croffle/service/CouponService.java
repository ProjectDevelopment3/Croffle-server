package com.sungshin.croffle.service;

import com.sungshin.croffle.domain.Coupon;
import com.sungshin.croffle.domain.jpa.CouponRepository;
import com.sungshin.croffle.dto.coupon.CouponListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;

    public List<CouponListResponseDto> couponList(Long userId) {
        return couponRepository.findCouponList(userId);
    }

    public Coupon addCoupon(Long userId, Long cafeId) {
        Coupon entity = Coupon.builder()
                .cafeId(cafeId)
                .userId(userId)
                .expiredDate(LocalDateTime.now().plusMonths(1))
                .build();
        return couponRepository.save(entity);
    }
}
