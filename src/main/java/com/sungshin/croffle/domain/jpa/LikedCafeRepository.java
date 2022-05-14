package com.sungshin.croffle.domain.jpa;

import com.sungshin.croffle.domain.LikedCafe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikedCafeRepository extends JpaRepository<LikedCafe, Long> {
}
