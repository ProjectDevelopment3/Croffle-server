package com.sungshin.croffle.controller;

import com.sungshin.croffle.config.auth.UserPrincipal;
import com.sungshin.croffle.dto.Response;
import com.sungshin.croffle.dto.board.BoardRequestDto;
import com.sungshin.croffle.dto.board.BoardListDto;
import com.sungshin.croffle.dto.board.BoardSearchDto;
import com.sungshin.croffle.dto.board.BoardUpdateDto;
import com.sungshin.croffle.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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
    public Response write(Authentication authentication,
                          @RequestBody BoardRequestDto boardRequestDto) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        boardRequestDto.setUserId(userPrincipal.getId());
        boardService.savePost(boardRequestDto);
        return Response.builder()
                .code("201")
                .messages("게시물 작성이 완료 되었습니다.")
                .build();
    }

    //게시글 개별 조회
    @GetMapping("/board/{id}")
    public Response<BoardSearchDto> findById(@PathVariable Long id) {
        List<BoardSearchDto> list = new ArrayList<BoardSearchDto>();
        list.add(boardService.getPost(id));
        return Response.<BoardSearchDto>builder()
                .code("200")
                .messages("게시글 조회가 완료 되었습니다.")
                .data(list)
                .build();
    }

    //게시판 목록 조회
    @GetMapping("/boards")
    public Response<BoardListDto> postList() {
        return Response.<BoardListDto>builder()
                .code("200")
                .messages("게시판 조회가 완료되었습니다.")
                .data(boardService.getAllPost())
                .build();
    }

    //게시물 수정
    @PutMapping("/board/{id}")
    public Response<BoardSearchDto> update(Authentication authentication,
                                            @PathVariable Long id, @RequestBody BoardUpdateDto boardUpdateDto) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        if (boardService.updatePost(id, boardUpdateDto, userPrincipal.getId()) < 0L) {
            return Response.<BoardSearchDto>builder()
                    .code("403")
                    .messages("수정 권한이 없습니다.")
                    .build();
        }
        return Response.<BoardSearchDto>builder()
                .code("201")
                .messages("게시글 수정이 완료 되었습니다.")
                .data(Collections.singletonList(boardService.getPost(id)))
                .build();
    }

    //게시물 삭제
    @DeleteMapping("/board/{id}")
    public Response delete(@PathVariable Long id, Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        if (!boardService.deletePost(id, userPrincipal.getId())) {
            return Response.builder()
                    .code("403")
                    .messages("삭제 권한이 없습니다.")
                    .build();
        }
        return Response.builder()
                .code("200")
                .messages("게시글 삭제가 완료 되었습니다.")
                .build();
    }


}
