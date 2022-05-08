package com.sungshin.croffle.service;

import com.sungshin.croffle.domain.Cafe;
import com.sungshin.croffle.domain.jpa.CafeRepository;
import com.sungshin.croffle.dto.cafe.CafeDetailDto;
import com.sungshin.croffle.dto.cafe.CafeListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CafeService {

    private final CafeRepository cafeRepository;

    public List<CafeListDto> findCafes() {
        List<Cafe> cafeList = cafeRepository.findAll();
        return cafeList.stream().map(CafeListDto::new).collect(Collectors.toList());
    }

    public CafeDetailDto cafeDetailSearch(Long cafe_id) {
        return new CafeDetailDto(
                cafeRepository.findById(cafe_id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카페 id입니다.")));
    }
}
