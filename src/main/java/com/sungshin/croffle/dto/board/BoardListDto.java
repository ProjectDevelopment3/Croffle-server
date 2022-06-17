package com.sungshin.croffle.dto.board;

import com.sungshin.croffle.domain.board.Board;
import com.sungshin.croffle.domain.board.BoardCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardListDto {
    private Long id;
    private String nickname;
    private String title;
    private BoardCategory boardCategory;
    private LocalDateTime modifiedDate;

    /*
    public BoardListDto(Board entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.boardCategory = entity.getBoardCategory();
        this.modifiedDate = entity.getModifiedDate();
    }
     */

    public BoardListDto(BoardListWrapper boardListWrapper){

        this.id = getId();
        this.title = getTitle();
        this.nickname = getNickname();
        this.boardCategory = getBoardCategory();
        this.modifiedDate = getModifiedDate();
    }

    @Override
    public String toString() {
        return "BoardListDto{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", title='" + title + '\'' +
                ", boardCategory=" + boardCategory +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}
