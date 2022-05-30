package com.sungshin.croffle.service;

import com.sungshin.croffle.domain.board.Board;
import com.sungshin.croffle.dto.board.BoardDto;
import com.sungshin.croffle.dto.board.BoardListDto;
import com.sungshin.croffle.dto.board.BoardUpdateDto;
import com.sungshin.croffle.domain.jpa.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public Long savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }

    public BoardDto getPost(Long id) {
        Board board = boardRepository.findById(id).get();
        return new BoardDto(board);

    }

    @Transactional(readOnly = true)
    public List<BoardListDto> getAllPost() {
        return boardRepository.findAllByOrderByIdDesc().stream()
                .map(BoardListDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long updatePost(Long id, BoardUpdateDto boardUpdateDto, Long userid) {
        Board post = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        if (post.getUserId().equals(userid)) {
            post.update(boardUpdateDto.getTitle(), boardUpdateDto.getContent(), boardUpdateDto.getBoardCategory(), boardUpdateDto.getModifiedDate());
            return id;
        }
        return -1L;
    }

    @Transactional
    public boolean deletePost(Long id, Long userid) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다. id =" + id));
        if (board.getUserId().equals(userid)) {
            boardRepository.delete(board);
            return true;
        }
        return false;
    }


}
