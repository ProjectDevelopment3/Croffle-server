package com.sungshin.croffle.domain.user;

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
    private String naverId;

    @Column(length = 20)
    private String nickname;

    @Column(length = 10)
    private String name;

    @Column(length = 20)
    private String phone;

    private String profileUrl;

    @ColumnDefault("0")
    private int owner;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(Long id, String naverId, String name, String nickname, String phone, String profileUrl, Role role) {
        this.id = id;
        this.naverId = naverId;
        this.name = name;
        this.nickname = nickname;
        this.phone = phone;
        this.profileUrl = profileUrl;
        this.role = role;
    }

    public User update(String phone, String profileUrl) {
        // oauth 받아온 정보로 업데이트
        this.phone = phone;
        this.profileUrl = profileUrl;
        return this;
    }

    public User nicknameEdit(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", naverId='" + naverId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", profileUrl='" + profileUrl + '\'' +
                ", owner=" + owner +
                ", role=" + role +
                '}';
    }
}
