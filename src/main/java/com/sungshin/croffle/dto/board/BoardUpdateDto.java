package com.sungshin.croffle.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardUpdateDto {
    private String title;
    private String content;
    private String boardCategory;

    @Builder
    public BoardUpdateDto (String title, String content, String boardCategory){
         this.title = title;
         this.content = content;
         this.boardCategory = boardCategory;
    }

}
