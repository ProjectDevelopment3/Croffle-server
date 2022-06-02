package com.sungshin.croffle.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cafeId;

    private Long userId;

    @Column(updatable = false)
    private LocalDateTime expiredDate;

    @Builder
    public Coupon(Long id, Long cafeId, Long userId, LocalDateTime expiredDate) {
        this.id = id;
        this.cafeId = cafeId;
        this.userId = userId;
        this.expiredDate = expiredDate;
    }
}
