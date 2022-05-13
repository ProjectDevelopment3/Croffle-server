package com.sungshin.croffle.domain.jpa;

import com.sungshin.croffle.domain.LikedCafe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikedCafeRepository extends JpaRepository<LikedCafe, Long> {
    Optional<LikedCafe> findByCafe_idAndUser_id(Long cafe_id, Long user_id);
}
