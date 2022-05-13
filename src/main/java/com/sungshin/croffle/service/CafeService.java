package com.sungshin.croffle.service;

import com.sungshin.croffle.domain.Cafe;
import com.sungshin.croffle.domain.LikedCafe;
import com.sungshin.croffle.domain.jpa.CafeRepository;
import com.sungshin.croffle.domain.jpa.LikedCafeRepository;
import com.sungshin.croffle.dto.cafe.CafeDetailDto;
import com.sungshin.croffle.dto.cafe.CafeListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CafeService {

    private final CafeRepository cafeRepository;
    private final LikedCafeRepository likedCafeRepository;

    public List<CafeListDto> findCafes() {
        List<Cafe> cafeList = cafeRepository.findAll();
        return cafeList.stream().map(CafeListDto::new).collect(Collectors.toList());
    }

    public CafeDetailDto cafeDetailSearch(Long cafe_id) {
        return new CafeDetailDto(
                cafeRepository.findById(cafe_id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카페 id입니다.")));
    }

    @Transactional
    public Long likedCafeAdd(Long cafe_id) {
        Long user_id = 1L; // user login 연동 후 변경
        return likedCafeRepository.save(new LikedCafe(cafe_id, user_id)).getId();
    }

    @Transactional
    public void likedCafeDelete(Long cafe_id, Long user_id) {
        LikedCafe entity = likedCafeRepository.findByCafeIdAndUserId(cafe_id, user_id)
                .orElseThrow(() -> new IllegalArgumentException(cafe_id + "가 스크랩에 존재하지 않습니다."));
        likedCafeRepository.delete(entity);
    }
}
