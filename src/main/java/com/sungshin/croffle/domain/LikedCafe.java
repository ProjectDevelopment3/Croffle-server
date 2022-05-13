package com.sungshin.croffle.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class LikedCafe {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long cafe_id;

//    @Column(nullable = false)
    private Long user_id;

    @Builder
    public LikedCafe(Long cafe_id, Long user_id) {
        this.cafe_id = cafe_id;
        this.user_id = user_id;
    }
}
