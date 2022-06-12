package com.sungshin.croffle.service;

import com.sungshin.croffle.domain.Review;
import com.sungshin.croffle.domain.jpa.ReviewRepository;
import com.sungshin.croffle.dto.review.ReviewRequestDto;
import com.sungshin.croffle.dto.review.SearchReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Transactional
    public Long saveReview(ReviewRequestDto reviewRequestDto, Long userid){
        return reviewRepository.save(reviewRequestDto.toEntity(userid)).getId();
    }

    @Transactional(readOnly = true)
    public List<SearchReviewDto> searchReview(Long cafe_id){
        List<SearchReviewDto> reviewList = new ArrayList<SearchReviewDto>();
        List<Review> reviews = reviewRepository.findByCafeId(cafe_id);

        for(int i = 0; i < reviews.size(); i++){
            Review review = reviews.get(i);
            reviewList.add(new SearchReviewDto(review));
        }
        return reviewList;
    }

}
