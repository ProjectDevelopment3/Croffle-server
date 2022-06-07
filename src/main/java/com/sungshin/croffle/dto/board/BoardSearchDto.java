package com.sungshin.croffle.dto.board;

import com.sungshin.croffle.domain.board.Board;
import com.sungshin.croffle.domain.board.BoardCategory;


import java.time.LocalDateTime;

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
