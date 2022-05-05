package com.sungshin.croffle.controller;

import com.sungshin.croffle.domain.dto.ReviewDto;
import com.sungshin.croffle.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/review/write")
    public String writeReview(ReviewDto reviewDto){
        reviewService.saveReview(reviewDto);
        return
    }


}
