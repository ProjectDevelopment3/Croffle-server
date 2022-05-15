package com.sungshin.croffle.domain.jpa;

import com.sungshin.croffle.domain.Review;
import com.sungshin.croffle.domain.board.Board;
import com.sungshin.croffle.dto.review.ReviewListDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<ReviewListDto> findByCafeId(Long cafe_id);
}
