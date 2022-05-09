package com.sungshin.croffle.controller;

import com.sungshin.croffle.domain.dto.ReviewDto;
import com.sungshin.croffle.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/review/write")
    public String writeReview(@RequestBody ReviewDto reviewDto){
        reviewService.saveReview(reviewDto);
        //TO-DO 추후에 응답 메세지 반환으로 수정
        return "redirect:/";
    }


}
