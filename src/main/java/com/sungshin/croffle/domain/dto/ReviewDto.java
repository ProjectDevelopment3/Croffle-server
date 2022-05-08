package com.sungshin.croffle.domain.dto;

import com.sungshin.croffle.domain.BaseTimeEntity;
import com.sungshin.croffle.domain.review.Review;
import lombok.Builder;

import java.time.LocalDateTime;

public class ReviewDto {
    private Long user_id;
    private Long cafe_id;
    private int rate;
    private String content;
    private LocalDateTime createdate;

    public Review toEntity() {
        return Review.builder()
                .user_id(user_id)
                .cafe_id(cafe_id)
                .rate(rate)
                .content(content)
                .build();
    }

    @Builder
    public ReviewDto(Long user_id, Long cafe_id ,int rate, String content){
         this.user_id = user_id;
         this.cafe_id = cafe_id;
         this.rate = rate;
         this.content = content;
    }



}
