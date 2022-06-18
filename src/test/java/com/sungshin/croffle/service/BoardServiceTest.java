package com.sungshin.croffle.service;

import com.sungshin.croffle.domain.board.Board;
import com.sungshin.croffle.domain.board.BoardCategory;
import com.sungshin.croffle.domain.jpa.BoardRepository;
import com.sungshin.croffle.domain.jpa.UserRepository;
import com.sungshin.croffle.domain.user.Role;
import com.sungshin.croffle.domain.user.User;
import com.sungshin.croffle.dto.board.BoardRequestDto;
import com.sungshin.croffle.dto.board.BoardSearchWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class BoardServiceTest {

    @Autowired
    BoardService boardService;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    UserRepository userRepository;
    Long userId;
    Long boardId;

    @BeforeEach
    public void beforeEach() {
        BoardRequestDto boardRequestDto = BoardRequestDto.builder()
                .title("테스트 제목")
                .content("테스트 본문")
                .boardCategory(BoardCategory.TALK)
                .build();
        User user = User.builder()
                .name("testuser")
                .naverId("testtesttesttest")
                .nickname("testnickname")
                .owner(0L)
                .role(Role.USER)
                .build();
        user = userRepository.save(user);
        boardRequestDto.setUserId(user.getId());
        userId = user.getId();
        boardId = boardService.savePost(boardRequestDto);
    }

    @Test
    public void 게시글상세조회() {
        // given

        // when
        BoardSearchWrapper board = boardService.getPost(boardId);

        // then
        assertThat(board.getTitle()).isEqualTo("테스트 제목");
        assertThat(board.getCategory()).isEqualTo(BoardCategory.TALK.toString());
        assertThat(board.getContent()).isEqualTo("테스트 본문");
        assertThat(board.getNickname()).isEqualTo("testnickname");
    }
}
