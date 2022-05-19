package com.sungshin.croffle.dto;

import com.sungshin.croffle.dto.board.BoardDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class Response {
    private String code;
    private String messages;
    private List<Object> data;
    private BoardDto boardDto;

    @Builder
    public Response(String code, String messages, List<Object> data, BoardDto boardDto) {
        this.code = code;
        this.messages = messages;
        this.data = data;
        this.boardDto = boardDto;
    }
}
