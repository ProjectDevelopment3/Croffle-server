package com.sungshin.croffle.dto.review;

import com.sungshin.croffle.domain.Review;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewListDto {
    private Long id;
    private String author;
    private int rate;
    private String content;
    private LocalDateTime createdDate;

    public ReviewListDto(Review entity){
        this.id = entity.getId();
        //this.author = entity.getUser_id().get_nickname();
        this.rate = entity.getRate();
        this.content = entity.getContent();
        this.createdDate = entity.getCreatedDate();
    }


}
