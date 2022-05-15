package com.sungshin.croffle.dto.review;

import com.sungshin.croffle.domain.Review;
import lombok.Builder;

import java.time.LocalDateTime;

public class ReviewDto {
    private Long userId;
    private Long cafeId;
    private int rate;
    private String content;
    private LocalDateTime createdDate;

    public Review toEntity() {
        return Review.builder()
                .userId(userId)
                .cafeId(cafeId)
                .rate(rate)
                .content(content)
                .build();
    }

    @Builder
    public ReviewDto(Long user_id, Long cafe_id ,int rate, String content){
         this.userId = user_id;
         this.cafeId = cafe_id;
         this.rate = rate;
         this.content = content;
    }



}
