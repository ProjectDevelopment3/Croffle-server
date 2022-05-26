package com.sungshin.croffle.config.auth.dto;

import com.sungshin.croffle.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private Long id;
    private String nickname;
    private String phone;
    private String profileUrl;
    private String naverId;

    public SessionUser(User user) {
        this.nickname = user.getNickname();
        this.phone = user.getPhone();
        this.profileUrl = user.getProfileUrl();
        this.naverId = user.getNaverId();
        this.id = user.getId();
    }
}
