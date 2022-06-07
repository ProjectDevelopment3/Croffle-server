package com.sungshin.croffle.dto.review;

import com.sungshin.croffle.domain.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReviewRequestDto {
    private Long user_id;
    private Long cafe_id;
    private int rate;
    private String content;

    public Review toEntity(Long user_id) {
        return Review.builder()
                .user_id(user_id)
                .cafe_id(cafe_id)
                .rate(rate)
                .content(content)
                .build();
    }

    @Builder
    public ReviewRequestDto(Long user_id, Long cafe_id , int rate, String content){
         this.user_id = user_id;
         this.cafe_id = cafe_id;
         this.rate = rate;
         this.content = content;
    }



}
