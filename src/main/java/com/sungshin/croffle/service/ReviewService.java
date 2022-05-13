package com.sungshin.croffle.service;

import com.sungshin.croffle.dto.review.ReviewDto;
import com.sungshin.croffle.domain.jpa.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Transactional
    public Long saveReview(ReviewDto reviewDto){
        return reviewRepository.save(reviewDto.toEntity()).getId();
    }

}
