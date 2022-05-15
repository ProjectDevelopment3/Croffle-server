package com.sungshin.croffle.controller;

import com.sungshin.croffle.dto.Response;
import com.sungshin.croffle.dto.review.ReviewDto;
import com.sungshin.croffle.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/review")
    public Response writeReview(@RequestBody ReviewDto reviewDto){
        reviewService.saveReview(reviewDto);

        return Response.builder()
                .code("201")
                .messages("리뷰 작성에 성공했습니다.")
                .build();

    }

    @GetMapping("/review/{cafeid}")
    public Response reviewList(@PathVariable Long cafe_id){
        return Response.builder()
                .code("200")
                .messages("리뷰 조회가 완료 되었습니다.")
                .data(Collections.singletonList(reviewService.getCafeReview(cafe_id)))
                .build();


    }





}
