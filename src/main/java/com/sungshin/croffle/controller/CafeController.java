package com.sungshin.croffle.controller;

import com.sungshin.croffle.dto.Response;
import com.sungshin.croffle.dto.cafe.CafeListDto;
import com.sungshin.croffle.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
