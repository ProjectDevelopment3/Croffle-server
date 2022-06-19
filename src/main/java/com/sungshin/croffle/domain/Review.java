package com.sungshin.croffle.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


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

    @Builder
    public Review(Long id, Long user_id, Long cafe_id, int rate, String content){
        this.id = id;
        this.userId = user_id;
        this.cafeId = cafe_id;
        this.rate = rate;
        this.content = content;
    }

}
