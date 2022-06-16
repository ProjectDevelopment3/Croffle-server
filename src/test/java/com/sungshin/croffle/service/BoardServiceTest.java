package com.sungshin.croffle.service;

import com.sungshin.croffle.domain.board.Board;
import com.sungshin.croffle.domain.board.BoardCategory;
import com.sungshin.croffle.domain.jpa.BoardRepository;
import com.sungshin.croffle.dto.board.BoardRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class BoardServiceTest {

    @Autowired
    BoardService boardService;
    @Autowired
    BoardRepository boardRepository;

    @BeforeEach
    public void beforeEach() {
        BoardRequestDto boardRequestDto = BoardRequestDto.builder()
                .title("테스트 제목")
                .content("테스트 본문")
                .boardCategory(BoardCategory.TALK)
                .build();
        boardRequestDto.setUserId(1L);
        boardService.savePost(boardRequestDto);
    }

    @Test
    public void 게시판글상세조회() {

    }
}
