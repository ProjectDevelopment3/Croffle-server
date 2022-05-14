package com.sungshin.croffle.controller;

import com.sungshin.croffle.dto.Response;
import com.sungshin.croffle.dto.board.BoardDto;
import com.sungshin.croffle.dto.board.BoardListDto;
import com.sungshin.croffle.dto.board.BoardUpdateDto;
import com.sungshin.croffle.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor

public class BoardController {
    private final BoardService boardService;

    //게시물 작성
    @PostMapping("/board/write")
    public Response write(@RequestBody BoardDto boardDto) {
        boardService.savePost(boardDto);
        return Response.builder()
                .code("201")
                .messages("게시물 작성이 완료 되었습니다.")
                .build();
    }

    //게시글 개별 조회
    @GetMapping("/board/{id}")
    public BoardDto findById(@PathVariable Long id) {
        return boardService.getPost(id);
    }

    //게시판 목록 조회
    @GetMapping("/board")
    public Response postList() {
        return Response.builder()
                .code("200")
                .messages("게시판 조회가 완료되었습니다.")
                .data(Collections.singletonList(boardService.getAllPost()))
                .build();
    }

    //게시물 수정
    @PutMapping("/board/{id}")
    public Response update(@PathVariable Long id, @RequestBody BoardUpdateDto boardUpdateDto) {
        boardService.update(id, boardUpdateDto);
        return Response.builder()
                .code("201")
                .messages("게시글 수정이 완료 되었습니다.")
                .build();
    }

    //게시물 삭제
    @DeleteMapping("/board/del/{id}")
    public Response delete(@PathVariable Long id) {
        boardService.delete(id);
        return Response.builder()
                .code("200")
                .messages("게시글 삭제가 완료 되었습니다.")
                .build();
    }


}
