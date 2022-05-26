package com.sungshin.croffle.controller;

import com.sungshin.croffle.dto.Response;
import com.sungshin.croffle.dto.cafe.*;
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
    public Response<CafeListDto> findAllCafe() {
        List<CafeListDto> cafes = cafeService.findCafes();
        return Response.<CafeListDto>builder()
                .code("200")
                .messages("등록된 카페들의 리스트 조회 성공")
                .data(cafes)
                .build();
    }

    @GetMapping("/cafe")
    public Response<CafeDetailDto> cafedetails(@RequestParam Long id) {
        return Response.<CafeDetailDto>builder()
                .code("200")
                .messages("카페 상세 조회 성공")
                .data(Collections.singletonList(cafeService.cafeDetailSearch(id)))
                .build();
    }

    // 카페 추천 기능
    @GetMapping("/cafe/recommend")
    public Response<CafeRecommendWrapper> recommendCafe(@RequestParam String filter) {
        if (!filter.equals("liked") && !filter.equals("review")) {
        }
        return Response.<CafeRecommendWrapper>builder()
                .code("200")
                .messages("카페 추천 성공")
                .data(cafeService.cafeRecommend(filter))
                .build();
    }

    // 스크랩 기능
    @GetMapping("/likes")
    public Response<CafeListDto> likedcafesearch() {
        // 로그인 쿠키에서 user id 얻어오기
        Long user_id = 1L;
        return Response.<CafeListDto>builder()
                .code("200")
                .messages("카페 스크랩 리스트 조회에 성공하였습니다.")
                .data(cafeService.findLikedCafes(user_id))
                .build();
    }

    @PostMapping("/like")
    public Response<Long> likedcafeAdd(@RequestBody LikedCafeRequestDto cafeAddRequestDto) {
        return Response.<Long>builder()
                .code("201")
                .messages("카페 스크랩 추가에 성공하였습니다.")
                .data(Collections.singletonList(cafeService.likedCafeAdd(cafeAddRequestDto.getCafe())))
                .build();
    }

    @DeleteMapping("/like/{id}")
    public Response likecafeDelete(@RequestParam Long id) {
        // user id 정보 service 에 넘겨주기, 해당 user 가 가진 데이터가 맞는지 확인 후 삭제
        cafeService.likedCafeDelete(id, 1L);
        return Response.builder()
                .code("200")
                .messages("카페 스크랩 삭제에 성공하였습니다.")
                .build();
    }
}
