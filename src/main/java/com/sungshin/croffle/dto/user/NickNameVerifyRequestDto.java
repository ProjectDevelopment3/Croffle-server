package com.sungshin.croffle.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NickNameVerifyRequestDto {
    private String nickname;

    @Builder
    public NickNameVerifyRequestDto(String nickname) {
        this.nickname = nickname;
    }
}
