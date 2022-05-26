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

    @Builder
    public UserDto(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public UserDto toDto(User user) {
        id = user.getId();
        nickname = user.getNickname();
        return this;
    }
}
