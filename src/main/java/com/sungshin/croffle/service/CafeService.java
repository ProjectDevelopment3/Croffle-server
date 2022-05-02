package com.sungshin.croffle.service;

import com.sungshin.croffle.domain.Cafe;
import com.sungshin.croffle.domain.jpa.CafeRepository;
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
}
