package com.sungshin.croffle.dto.board;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sungshin.croffle.domain.board.Board;
import com.sungshin.croffle.domain.board.BoardCategory;
import lombok.Getter;

import java.time.LocalDateTime;

@JsonSerialize
@Getter
public class BoardSearchDto {
    private String title;
    private String nickName;
    private String content;
    private LocalDateTime modifiedDate;
    private BoardCategory category;


    public BoardSearchDto(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
        this.category = board.getBoardCategory();
        this.modifiedDate = board.getModifiedDate();
    }


}
