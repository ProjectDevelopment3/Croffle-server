package com.sungshin.croffle.dto.review;
import com.sungshin.croffle.domain.Review;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SearchReviewDto {
    private Long id;
    private int rate;
    private String content;
    private LocalDateTime createdDate;

    public SearchReviewDto(Review review) {
        this.id = review.getId();
        this.rate = review.getRate();
        this.content = review.getContent();
        this.createdDate = review.getCreatedDate();
    }

}
