package com.sungshin.croffle.dto.board;

import com.sungshin.croffle.domain.board.Board;
import com.sungshin.croffle.domain.board.BoardCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class BoardListDto {
    private Long id;
    private String user_nickname;
    private String title;
    private BoardCategory boardCategory;
    private LocalDateTime modifiedDate;

    public BoardListDto(Board entity){
        this.id = entity.getId();
        //this.user_nickname = entity.getUser_id().getNickname();
        this.title = entity.getTitle();
        this.boardCategory = entity.getBoardCategory();
        this.modifiedDate = entity.getModifiedDate();
    }

}
