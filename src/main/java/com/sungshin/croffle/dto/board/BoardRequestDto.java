package com.sungshin.croffle.dto.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sungshin.croffle.domain.board.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class BoardRequestDto {

    @JsonIgnore
    private Long userId;
    private String title;
    private String content;
    private String boardCategory;

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
    public BoardRequestDto(Long user_id, String title, String content, String boardCategory){
        this.userId = user_id;
        this.title = title;
        this.content = content;
        this.boardCategory = boardCategory;
    }


    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
