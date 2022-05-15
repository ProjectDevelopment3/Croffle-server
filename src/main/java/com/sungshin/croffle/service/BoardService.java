package com.sungshin.croffle.service;

import com.sungshin.croffle.domain.board.Board;
import com.sungshin.croffle.dto.board.BoardDto;
import com.sungshin.croffle.dto.board.BoardListDto;
import com.sungshin.croffle.dto.board.BoardUpdateDto;
import com.sungshin.croffle.domain.jpa.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public Long savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }


    public BoardDto getPost(Long id) {
        Board board = boardRepository.findById(id).get();

        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .user_id(board.getUser_id())
                .title(board.getTitle())
                .content(board.getContent())
                .boardCategory(board.getBoardCategory())
                .createdDate(board.getCreatedDate())
                .modifiedDateDate(board.getModifiedDate())
                .build();

        return boardDto;

    }


    public List<BoardListDto> getAllPost() {
        return boardRepository.findAllByOrderByIdDesc().stream()
                .map(BoardListDto::new)
                .collect(Collectors.toList());
    }

    public Long updatePost(Long id, BoardUpdateDto boardUpdateDto) {
        Board post = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        post.update(boardUpdateDto.getTitle(), boardUpdateDto.getContent(), boardUpdateDto.getBoardCategory(), boardUpdateDto.getModifiedDate());
        return id;
    }

    public void deletePost(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다. id =" + id));
        boardRepository.delete(board);
    }


}
