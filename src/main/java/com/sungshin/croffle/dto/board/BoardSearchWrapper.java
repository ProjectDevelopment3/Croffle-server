package com.sungshin.croffle.dto.board;

import java.time.LocalDateTime;

public interface BoardSearchWrapper {
    String getTitle();
    String getContent();
    String getNickname();
    LocalDateTime getModifiedDate();
    String getCategory();
}
