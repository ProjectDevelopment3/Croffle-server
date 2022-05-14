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
                .code("2010")
                .messages("카페 스크랩 추가에 성공하였습니다.")
                .data(Collections.singletonList(cafeService.likedCafeAdd(cafeAddRequestDto.getCafe())))
                .build();
    }
}
