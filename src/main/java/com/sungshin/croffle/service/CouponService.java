package com.sungshin.croffle.service;

import com.sungshin.croffle.domain.Coupon;
import com.sungshin.croffle.domain.jpa.CouponRepository;
import com.sungshin.croffle.dto.coupon.CouponListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;

    public List<CouponListResponseDto> couponList(Long userId) {
        return couponRepository.findCouponList(userId);
    }

    @Transactional
    public Coupon addCoupon(Long userId, Long cafeId) {
        Coupon entity = Coupon.builder()
                .cafeId(cafeId)
                .userId(userId)
                .expiredDate(LocalDateTime.now().plusMonths(1))
                .build();
        return couponRepository.save(entity);
    }

    @Transactional
    public boolean useCoupon(Long userId, Long couponId) {
        try {
            Coupon entity = couponRepository.findById(couponId)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 쿠폰입니다."));
            if (entity.getUserId() == userId) {
                return false;
            }
            couponRepository.delete(entity);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
