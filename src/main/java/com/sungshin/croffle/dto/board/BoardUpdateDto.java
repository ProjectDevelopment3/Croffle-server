package com.sungshin.croffle.dto.board;

import com.sungshin.croffle.domain.board.BoardCategory;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardUpdateDto {
    private String title;
    private String content;
    private BoardCategory boardCategory;

    @Builder
    public BoardUpdateDto (String title, String content, BoardCategory boardCategory){
         this.title = title;
         this.content = content;
         this.boardCategory = boardCategory;
    }

}
