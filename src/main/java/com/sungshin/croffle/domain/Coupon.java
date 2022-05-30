package com.sungshin.croffle.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cafeId;

    private Long userId;

    @Column(updatable = false)
    private String expiredDate;

    @Builder
    public Coupon(Long id, Long cafeId, Long userId, String expiredDate) {
        this.id = id;
        this.cafeId = cafeId;
        this.userId = userId;
        this.expiredDate = expiredDate;
    }
}
