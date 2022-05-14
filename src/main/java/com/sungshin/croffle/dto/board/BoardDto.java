package com.sungshin.croffle.dto.board;

import com.sungshin.croffle.domain.board.Board;
import com.sungshin.croffle.domain.board.BoardCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardDto {
    private Long id;
    private Long user_id;
    private String title;
    private String content;
    private BoardCategory boardCategory;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Board toEntity() {
        Board build = Board.builder()
                .id(id)
                .user_id(user_id)
                .title(title)
                .content(content)
                .boardCategory(boardCategory)
                .build();
        return build;
    }


    @Builder
    public BoardDto(Long id, Long user_id, String title, String content, BoardCategory boardCategory, LocalDateTime createdDate, LocalDateTime modifiedDateDate){
        this.id = id;
        this.user_id = user_id;
        this.title = title;
        this.content = content;
        this.boardCategory = boardCategory;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDateDate;
    }





}
