package com.sungshin.croffle.dto.review;

import com.sungshin.croffle.domain.review.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReviewDto {
    private Long cafe_id;
    private int rate;
    private String content;
    private LocalDateTime createdate;

    public Review toEntity(Long user_id) {
        return Review.builder()
                .user_id(user_id)
                .cafe_id(cafe_id)
                .rate(rate)
                .content(content)
                .build();
    }

    @Builder
    public ReviewDto(Long cafe_id ,int rate, String content){
         this.cafe_id = cafe_id;
         this.rate = rate;
         this.content = content;
    }



}
