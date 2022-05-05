package com.sungshin.croffle.domain.dto;

import com.sungshin.croffle.domain.review.Review;
import lombok.Builder;

import java.time.LocalDateTime;

public class ReviewDto {
    private Long id;
    private User user_id;
    private Cafe cafe_id;
    private int rate;
    private String content;
    private LocalDateTime createdate;

    public Review toEntity() {
        return Review.builder()
                .id(id)
                .user_id(user_id)
                .cafe_id(cafe_id)
                .rate(rate)
                .content(content)
                .build();
    }

    @Builder
    public ReviewDto(Long id, User user_id, Cafe cafe_id, int rate, String content, LocalDateTime createdate){
         this.id = id;
         this.user_id = user_id;
         this.cafe_id = cafe_id;
         this.rate = rate;
         this.content = content;
         this.createdate = createdate;
    }



}
