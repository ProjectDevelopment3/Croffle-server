package com.sungshin.croffle.service;

import com.sungshin.croffle.dto.review.ReviewDto;
import com.sungshin.croffle.domain.jpa.ReviewRepository;
import com.sungshin.croffle.dto.review.ReviewListDto;
import com.sungshin.croffle.dto.review.ReviewResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Transactional
    public Long saveReview(ReviewDto reviewDto){
        return reviewRepository.save(reviewDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<ReviewResponseDto> getCafeReview(Long cafe_id){
        return reviewRepository.findByCafeId(cafe_id);
    }

}
