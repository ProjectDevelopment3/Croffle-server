package com.sungshin.croffle.domain.review;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity

public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    @ManyToOne
    @JoinColumn(name = "cafe_id")
    private Cafe cafe_id;

    private int rate;

    @Column(length = 250, nullable = false)
    private String content;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdate;

    @Builder
    public Review(Long id, User user_id, Cafe cafe_id, int rate, String content){
        this.id = id;
        this.user_id = user_id;
        this.cafe_id = cafe_id;
        this.rate = rate;
        this.content = content;
    }

}
