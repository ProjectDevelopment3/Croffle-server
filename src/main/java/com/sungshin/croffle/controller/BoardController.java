package com.sungshin.croffle.controller;

import com.sungshin.croffle.dto.Response;
import com.sungshin.croffle.dto.board.BoardDto;
import com.sungshin.croffle.dto.board.BoardListDto;
import com.sungshin.croffle.dto.board.BoardUpdateDto;
import com.sungshin.croffle.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor

public class BoardController {
    private final BoardService boardService;

    //게시물 작성
    @PostMapping("/board")
    public Response write(@RequestBody BoardDto boardDto) {
        boardService.savePost(boardDto);
        return Response.builder()
                .code("201")
                .messages("게시물 작성이 완료 되었습니다.")
                .build();
    }

    //게시글 개별 조회
    @GetMapping("/board/{id}")
    public Response<BoardDto> findById(@PathVariable Long id) {
        List<BoardDto> list = new ArrayList<BoardDto>();
        list.add(boardService.getPost(id));
        return Response.<BoardDto>builder()
                .code("200")
                .messages("게시글 조회가 완료 되었습니다.")
                .data(list)
                .build();
    }

    //게시판 목록 조회
    @GetMapping("/board")
    public Response<BoardListDto> postList() {
        return Response.<BoardListDto>builder()
                .code("200")
                .messages("게시판 조회가 완료되었습니다.")
                .data(boardService.getAllPost())
                .build();
    }

    //게시물 수정
    @PutMapping("/board/{id}")
    public Response<BoardDto> update(@PathVariable Long id, @RequestBody BoardUpdateDto boardUpdateDto) {
        boardService.updatePost(id, boardUpdateDto);
        return Response.<BoardDto>builder()
                .code("201")
                .messages("게시글 수정이 완료 되었습니다.")
                .data(Collections.singletonList(boardService.getPost(id)))
                .build();
    }

    //게시물 삭제
    @DeleteMapping("/board/{id}")
    public Response delete(@PathVariable Long id) {
        boardService.deletePost(id);
        return Response.builder()
                .code("200")
                .messages("게시글 삭제가 완료 되었습니다.")
                .build();
    }

}
