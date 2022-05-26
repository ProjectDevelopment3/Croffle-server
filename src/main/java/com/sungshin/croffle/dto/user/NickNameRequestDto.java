package com.sungshin.croffle.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NickNameRequestDto {
    private String nickname;

    @Builder
    public NickNameRequestDto(String nickname) {
        this.nickname = nickname;
    }
}
