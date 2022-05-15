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
    private Long cafeId;

//    @Column(nullable = false)
    private Long userId;

    @Builder
    public LikedCafe(Long cafeId, Long user_id) {
        this.cafeId = cafeId;
        this.userId = user_id;
    }
}
