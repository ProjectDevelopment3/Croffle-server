package com.sungshin.croffle.service;

import com.sungshin.croffle.domain.Cafe;
import com.sungshin.croffle.domain.LikedCafe;
import com.sungshin.croffle.domain.jpa.CafeRepository;
import com.sungshin.croffle.domain.jpa.LikedCafeRepository;
import com.sungshin.croffle.domain.jpa.UserRepository;
import com.sungshin.croffle.dto.cafe.CafeDetailDto;
import com.sungshin.croffle.dto.cafe.CafeListDto;
import com.sungshin.croffle.dto.cafe.CafeRecommendWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CafeService {

    private final CafeRepository cafeRepository;
    private final LikedCafeRepository likedCafeRepository;
    private final UserRepository userRepository;
    private final MenuService menuService;

    @Transactional(readOnly = true)
    public List<CafeListDto> findCafes() {
        List<Cafe> cafeList = cafeRepository.findAllByChecked(true);
        System.out.println(cafeList);
        return cafeList.stream().map(CafeListDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CafeListDto> findByCafeName(String name) {
        List<Cafe> cafeList = cafeRepository.findAllByNameLikeAndChecked("%" + name + "%", true);
        return cafeList.stream().map(CafeListDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CafeDetailDto cafeDetailSearch(Long cafe_id) {
        System.out.println(cafe_id);
        Cafe cafe = cafeRepository.findByIdAndChecked(cafe_id, true)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카페 id입니다."));
        CafeDetailDto cafeDetailDto = new CafeDetailDto(cafe);
        cafeDetailDto.setMenuListDtos(menuService.getCheckedMenuList(cafeDetailDto.getId()));
        return cafeDetailDto;
    }

    @Transactional(readOnly = true)
    public List<CafeRecommendWrapper> cafeRecommend(String filter) {
        List<CafeRecommendWrapper> list = null;
        if (filter.equals("review")) {
            list = cafeRepository.cafeRecommendOrderByReview();
        } else if (filter.equals("liked")) {
            list = cafeRepository.cafeRecommendOrderByLiked();
        }
        return list;
    }

    @Transactional(readOnly = true)
    public List<CafeListDto> findLikedCafes(Long user_id) {
        List<LikedCafe> likedCafes = likedCafeRepository.findByUserId(user_id);
        List<Cafe> cafeList = new ArrayList<>();
        for (int i = 0; i < likedCafes.size(); i++) {
            Long cafe_id = likedCafes.get(i).getCafeId();
            cafeList.add(cafeRepository.findById(cafe_id)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카페 id 입니다." + cafe_id)));
        }
        return cafeList.stream().map(CafeListDto::new).collect(Collectors.toList());
    }

    @Transactional
    public Long likedCafeAdd(Long cafe_id, Long user_id) {
        return likedCafeRepository.save(new LikedCafe(cafe_id, user_id)).getId();
    }

    @Transactional
    public void likedCafeDelete(Long cafe_id, Long user_id) {
        userRepository.findById(user_id)
                .orElseThrow(() -> new IllegalArgumentException(user_id + "= user id 가 존재하지 않습니다."));
        LikedCafe entity = likedCafeRepository.findByCafeIdAndUserId(cafe_id, user_id)
                .orElseThrow(() -> new IllegalArgumentException(cafe_id + "가 스크랩에 존재하지 않습니다."));
        likedCafeRepository.delete(entity);
    }
}
