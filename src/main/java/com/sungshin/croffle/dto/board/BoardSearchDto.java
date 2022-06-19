package com.sungshin.croffle.dto.board;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sungshin.croffle.domain.board.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@JsonSerialize
@Getter
public class BoardSearchDto {
    private String title;
    private String nickName;
    private String content;
    private LocalDateTime modifiedDate;
    private String boardCategory;


    public BoardSearchDto(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
        this.boardCategory = board.getBoardCategory();
        this.modifiedDate = board.getModifiedDate();
    }


}
