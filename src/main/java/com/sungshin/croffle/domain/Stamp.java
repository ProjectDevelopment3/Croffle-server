package com.sungshin.croffle.domain;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class Stamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cafeId;
    private Long userId;

    @ColumnDefault("0")
    private int stampCnt;

    @Builder
    public Stamp(Long id, Long cafeId, Long userId, int stampCnt) {
        this.id = id;
        this.cafeId = cafeId;
        this.userId = userId;
        this.stampCnt = stampCnt;
    }
}
