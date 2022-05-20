package com.sungshin.croffle.service;

import com.sungshin.croffle.domain.jpa.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;

}
