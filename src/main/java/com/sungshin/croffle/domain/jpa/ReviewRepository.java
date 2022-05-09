package com.sungshin.croffle.domain.jpa;

import com.sungshin.croffle.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {

}
