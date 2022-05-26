package com.sungshin.croffle.controller;

import com.sungshin.croffle.dto.Response;
import com.sungshin.croffle.dto.review.ReviewDto;
import com.sungshin.croffle.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/review")
    public Response writeReview(@RequestBody ReviewDto reviewDto){
        reviewService.saveReview(reviewDto);
        return Response.builder()
                .code("201")
                .messages("리뷰 작성에 성공하였습니다.")
                .build();
    }





}
