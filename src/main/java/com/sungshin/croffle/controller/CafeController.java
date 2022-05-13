package com.sungshin.croffle.controller;

import com.sungshin.croffle.dto.Response;
import com.sungshin.croffle.dto.cafe.CafeListDto;
import com.sungshin.croffle.dto.cafe.LikedCafeRequestDto;
import com.sungshin.croffle.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CafeController {

    private final CafeService cafeService;
    @GetMapping("/cafes")
    public Response findAllCafe() {
        List<CafeListDto> cafes = cafeService.findCafes();
        return Response.builder()
                .code("200")
                .messages("등록된 카페들의 리스트 조회 성공")
                .data(Collections.singletonList(cafes))
                .build();
    }

    @GetMapping("/cafe")
    public Response cafedetails(@RequestParam Long id) {
        return Response.builder()
                .code("200")
                .messages("카페 상세 조회 성공")
                .data(Collections.singletonList(cafeService.cafeDetailSearch(id)))
                .build();
    }

    // 스크랩 기능
    @PostMapping("/like")
    public Response likedcafeAdd(@RequestBody LikedCafeRequestDto cafeAddRequestDto) {
        return Response.builder()
                .code("201")
                .messages("카페 스크랩 추가에 성공하였습니다.")
                .data(Collections.singletonList(cafeService.likedCafeAdd(cafeAddRequestDto.getCafe())))
                .build();
    }

    @PostMapping("/like/del")
    public Response likecafeDelete(@RequestBody LikedCafeRequestDto cafeDelRequestDto) {
        // user id 정보 service 에 넘겨주기, 해당 user 가 가진 데이터가 맞는지 확인 후 삭제
        cafeService.likedCafeDelete(cafeDelRequestDto.getCafe(), 1L);
        return Response.builder()
                .code("200")
                .messages("카페 스크랩 삭제에 성공하였습니다.")
                .build();
    }
}
