package com.sungshin.croffle.domain.jpa;


import com.sungshin.croffle.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByCafeId(Long cafe_id);
}
