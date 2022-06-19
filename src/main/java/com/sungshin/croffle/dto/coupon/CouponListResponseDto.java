package com.sungshin.croffle.dto.coupon;

import java.time.LocalDateTime;

public interface CouponListResponseDto {
    Long getCouponId();
    String getCafeName();
    String getBenefit();
    LocalDateTime getExpiredDate();
}
