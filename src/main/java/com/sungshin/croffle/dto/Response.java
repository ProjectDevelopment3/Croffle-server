package com.sungshin.croffle.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class Response<T> {
    private String code;
    private String messages;
    private List<T> data;

    @Builder
    public Response(String code, String messages, List<T> data) {
        this.code = code;
        this.messages = messages;
        this.data = data;
    }
}
