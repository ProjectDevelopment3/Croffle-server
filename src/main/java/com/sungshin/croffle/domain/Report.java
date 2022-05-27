package com.sungshin.croffle.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long cafeId;

    @Column(length = 100, nullable = false)
    private String content;

    @Column(length = 30, nullable = false)
    private String category;


    @Builder
    public Report(Long user_id, Long cafe_id, String content, String category) {
        this.userId = user_id;
        this.cafeId = cafe_id;
        this.content = content;
        this.category = category;
    }

}