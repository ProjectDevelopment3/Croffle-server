package com.sungshin.croffle.domain.jpa;

import com.sungshin.croffle.domain.Coupon;
import com.sungshin.croffle.dto.coupon.CouponListResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    @Query(value = "select co.id couponId, ca.name cafeName, ca.benefit benefit, co.expired_date expiredDate from coupon co, cafe ca " +
            "where ca.id = co.cafe_id and co.user_id = :userId", nativeQuery = true)
    List<CouponListResponseDto> findCouponList(@Param("userId") Long userId);
}
