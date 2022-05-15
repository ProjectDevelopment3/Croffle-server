package com.sungshin.croffle.domain;


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

    private Long userId;

    private Long cafeId;

    private int rate;

    @Column(length = 250, nullable = false)
    private String content;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @Builder
    public Review(Long userId, Long cafeId, int rate, String content){
        this.userId = userId;
        this.cafeId= cafeId;
        this.rate = rate;
        this.content = content;
    }

}
