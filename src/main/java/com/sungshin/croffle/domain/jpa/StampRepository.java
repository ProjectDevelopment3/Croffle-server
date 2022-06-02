package com.sungshin.croffle.domain.jpa;

import com.sungshin.croffle.domain.Stamp;
import com.sungshin.croffle.dto.stamp.StampListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StampRepository extends JpaRepository<Stamp, Long> {
    @Query(value = "select c.name, s.stampCnt from stamp s, cafe c where s.cafeId = c.id and s.userId=:userId", nativeQuery = true)
    List<StampListDto> findStampAndCafeByUserId(@Param("userId") Long userId);

    Optional<Stamp> findByUserIdAndCafeId(Long userId, Long cafeId);
}
