package com.sungshin.croffle.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long naverId;

    @Column(length = 20)
    private String nickname;

    @Column(length = 20)
    private String phone;

    private String profileUrl;

    @ColumnDefault("0")
    private int owner;

    @Builder
    public User(Long id, Long naverId, String nickname, String phone, String profileUrl, int owner) {
        this.id = id;
        this.naverId = naverId;
        this.nickname = nickname;
        this.phone = phone;
        this.profileUrl = profileUrl;
        this.owner = owner;
    }

    @Builder
    public User update(String phone, String profileUrl) {
        // oauth 받아온 정보로 업데이트
        this.phone = phone;
        this.profileUrl = profileUrl;
        return this;
    }
}
