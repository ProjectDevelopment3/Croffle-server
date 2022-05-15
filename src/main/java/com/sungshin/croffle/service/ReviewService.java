package com.sungshin.croffle.service;

import com.sungshin.croffle.domain.Review;
import com.sungshin.croffle.dto.review.ReviewDto;
import com.sungshin.croffle.domain.jpa.ReviewRepository;
import com.sungshin.croffle.dto.review.ReviewListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Transactional
    public Long saveReview(ReviewDto reviewDto){
        return reviewRepository.save(reviewDto.toEntity()).getId();
    }

    
    //readOnly 를 사용하여 단순 조회-> 속도 향상
    @Transactional(readOnly = true)
    public List<ReviewListDto> getCafeReview(Long cafe_id){
       return reviewRepository.findByCafeId(cafe_id);
    }

}
