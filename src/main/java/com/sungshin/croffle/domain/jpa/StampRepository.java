package com.sungshin.croffle.domain.jpa;

import com.sungshin.croffle.domain.Stamp;
import com.sungshin.croffle.dto.stamp.StampListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StampRepository extends JpaRepository<Stamp, Long> {
    @Query(value = "select c.name cafeName, s.stamp_cnt stampCount " +
            "from stamp s, cafe c " +
            "where s.cafe_id = c.id and s.user_id=:userId", nativeQuery = true)
    List<StampListDto> findStampAndCafeByUserId(@Param("userId") Long userId);

    Optional<Stamp> findByUserIdAndCafeId(Long userId, Long cafeId);
}
