package com.sungshin.croffle.dto.user;

import com.sungshin.croffle.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String nickname;
    private String profile;

    @Builder
    public UserDto(Long id, String nickname, String profile) {
        this.id = id;
        this.nickname = nickname;
        this.profile = profile;
    }

    public UserDto toDto(User user) {
        id = user.getId();
        nickname = user.getNickname();
        profile = user.getProfileUrl();
        return this;
    }
}
