package com.sungshin.croffle.controller;

import com.sungshin.croffle.config.auth.UserPrincipal;
import com.sungshin.croffle.dto.Response;
import com.sungshin.croffle.dto.cafe.*;
import com.sungshin.croffle.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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

    @GetMapping("/cafe/search")
    public Response<CafeListDto> findByCafeName(@RequestParam String name) {
        List<CafeListDto> list = cafeService.findByCafeName(name);
        if (list.size() == 0) {
            return Response.<CafeListDto>builder()
                    .code("4040")
                    .messages("일치하는 카페가 없습니다.")
                    .data(list)
                    .build();
        }
        return Response.<CafeListDto>builder()
                .code("200")
                .messages("카페 검색 성공")
                .data(list)
                .build();
    }

    @GetMapping("/cafe/{id}")
    public Response<CafeDetailDto> cafedetails(@PathVariable Long id) {
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
            return Response.<CafeRecommendWrapper>builder()
                    .code("4000")
                    .messages("필터 이름이 잘못되었습니다.")
                    .build();
        }
        return Response.<CafeRecommendWrapper>builder()
                .code("200")
                .messages("카페 추천 성공")
                .data(cafeService.cafeRecommend(filter))
                .build();
    }

    // 스크랩 기능
    @GetMapping("/likes")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Response<CafeListDto> likedcafesearch(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return Response.<CafeListDto>builder()
                .code("200")
                .messages("카페 스크랩 리스트 조회에 성공하였습니다.")
                .data(cafeService.findLikedCafes(userPrincipal.getId()))
                .build();
    }

    @PostMapping("/like")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Response<Long> likedcafeAdd(Authentication authentication,
                                       @RequestBody LikedCafeRequestDto cafeAddRequestDto) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return Response.<Long>builder()
                .code("201")
                .messages("카페 스크랩 추가에 성공하였습니다.")
                .data(Collections.singletonList(
                        cafeService.likedCafeAdd(
                                cafeAddRequestDto.getCafe(), userPrincipal.getId())))
                .build();
    }

    @DeleteMapping("/like/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Response likecafeDelete(Authentication authentication,
                                   @RequestParam Long id) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        // user id 정보 service 에 넘겨주기, 해당 user 가 가진 데이터가 맞는지 확인 후 삭제
        cafeService.likedCafeDelete(id, userPrincipal.getId());
        return Response.builder()
                .code("200")
                .messages("카페 스크랩 삭제에 성공하였습니다.")
                .build();
    }
}
