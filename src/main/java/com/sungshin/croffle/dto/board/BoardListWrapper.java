package com.sungshin.croffle.dto.board;

import java.time.LocalDateTime;

public interface BoardListWrapper {
    Long getId();
    String getTitle();
    String getNickname();
    String getCategory();
    LocalDateTime getModifiedDate();
}
