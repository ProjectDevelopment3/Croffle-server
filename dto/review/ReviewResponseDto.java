package com.sungshin.croffle.dto.review;

import com.sungshin.croffle.domain.Cafe;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
public class ReviewResponseDto {
    private Long cafe_id;
    private List<ReviewListDto> reviewList;

    @Builder
    public ReviewResponseDto(Cafe cafe){
        /*cafe id와 reviewList를 동시에 가지고 올 수 있는 방법이 없어요......
        this.cafe_id = cafe.getId();
        this.reviewList = ReviewListDto.cafeReviewListDto(cafe.getReviews());
         */
    }





}
