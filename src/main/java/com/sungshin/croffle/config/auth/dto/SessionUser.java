package com.sungshin.croffle.config.auth.dto;

import com.sungshin.croffle.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String nickname;
    private String phone;
    private String profileUrl;

    public SessionUser(User user) {
        this.nickname = user.getNickname();
        this.phone = user.getPhone();
        this.profileUrl = user.getProfileUrl();
    }
}
