package com.sungshin.croffle.dto.review;

import com.sungshin.croffle.domain.Review;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class ReviewRequestDto {
    private Long userId;
    private Long cafeId;
    private int rate;
    private String content;

    public Review toEntity(Long user_id) {
        return Review.builder()
                .user_id(user_id)
                .cafe_id(cafeId)
                .rate(rate)
                .content(content)
                .build();
    }

    @Builder
    public ReviewRequestDto(Long user_id, Long cafe_id , int rate, String content){
         this.userId = user_id;
         this.cafeId = cafe_id;
         this.rate = rate;
         this.content = content;
    }



}
