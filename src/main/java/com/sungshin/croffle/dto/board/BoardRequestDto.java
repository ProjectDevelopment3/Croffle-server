package com.sungshin.croffle.dto.board;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sungshin.croffle.domain.board.Board;
import com.sungshin.croffle.domain.board.BoardCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonSerialize
public class BoardRequestDto {

    private Long userId;
    private String title;
    private String content;
    private BoardCategory boardCategory;

    public Board toEntity() {
        Board build = Board.builder()
                .user_id(userId)
                .title(title)
                .content(content)
                .boardCategory(boardCategory)
                .build();
        return build;
    }

    @Builder
    public BoardRequestDto(Long user_id, String title, String content, BoardCategory boardCategory){
        this.userId = user_id;
        this.title = title;
        this.content = content;
        this.boardCategory = boardCategory;
    }


    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
