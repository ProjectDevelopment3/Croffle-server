package com.sungshin.croffle.dto.board;

import com.sungshin.croffle.domain.board.BoardCategory;

import java.time.LocalDateTime;

public interface BoardListWrapper {
    Long getId();
    String getTitle();
    String getNickname();
    String getCategory();
    LocalDateTime getModifiedDate();
}
