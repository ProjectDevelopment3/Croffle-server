package com.sungshin.croffle.domain.review;


import com.sungshin.croffle.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity

public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long user_id;

    private Long cafe_id;

    private int rate;

    @Column(length = 250, nullable = false)
    private String content;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdate;

    @Builder
    public Review(Long id, Long user_id, Long cafe_id, int rate, String content){
        this.user_id = user_id;
        this.cafe_id = cafe_id;
        this.rate = rate;
        this.content = content;
    }

}
