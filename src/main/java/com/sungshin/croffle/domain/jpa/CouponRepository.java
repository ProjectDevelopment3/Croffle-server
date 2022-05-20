package com.sungshin.croffle.domain.jpa;

import com.sungshin.croffle.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
