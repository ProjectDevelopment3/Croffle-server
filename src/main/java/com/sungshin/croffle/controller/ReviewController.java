package com.sungshin.croffle.controller;

import com.sungshin.croffle.config.auth.UserPrincipal;
import com.sungshin.croffle.dto.Response;
import com.sungshin.croffle.dto.review.ReviewRequestDto;
import com.sungshin.croffle.dto.review.SearchReviewDto;
import com.sungshin.croffle.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/review")
    public Response writeReview(Authentication authentication,@RequestBody ReviewRequestDto reviewRequestDto){
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        reviewService.saveReview(reviewRequestDto, userPrincipal.getId());
        return Response.builder()
                .code("201")
                .messages("리뷰 작성에 성공하였습니다.")
                .build();
    }

    @GetMapping("/review/{cafe_id}")
    public Response searchReview(@PathVariable Long cafe_id){
        List<SearchReviewDto> reviewList = reviewService.searchReview(cafe_id);
        
        if(reviewList.size() == 0){
            return Response.<SearchReviewDto>builder()
                    .code("200")
                    .messages("일치하는 리뷰가 없습니다.")
                    .data(reviewList)
                    .build();
        }

        return Response.<SearchReviewDto>builder()
                .code("200")
                .messages("리뷰 조회에 성공하였습니다.")
                .data(reviewList)
                .build();

    }

}
