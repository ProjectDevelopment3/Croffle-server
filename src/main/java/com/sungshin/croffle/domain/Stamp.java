package com.sungshin.croffle.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Stamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long cafeId;

    @Column(nullable = false)
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

    public void addStampCnt() {
        stampCnt += 1;
    }
}
