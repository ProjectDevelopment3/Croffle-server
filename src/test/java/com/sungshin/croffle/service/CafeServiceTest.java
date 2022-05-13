package com.sungshin.croffle.service;

import com.sungshin.croffle.domain.Cafe;
import com.sungshin.croffle.domain.LikedCafe;
import com.sungshin.croffle.domain.jpa.CafeRepository;
import com.sungshin.croffle.domain.jpa.LikedCafeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class CafeServiceTest {

    @Autowired
    CafeService cafeService;
    @Autowired
    CafeRepository cafeRepository;
    @Autowired
    LikedCafeRepository likedCafeRepository;

    @BeforeEach
    public void beforeEach() {
        Cafe cafe = Cafe.builder()
                .name("테스트카페")
                .coords("10,10")
                .addr("서울시 강남구")
                .build();
        cafeRepository.save(cafe);
    }

    @Test
    public void 카페스크랩추가() throws Exception {
        // given
        Cafe cafe = cafeRepository.findByName("테스트카페").get();

        // when
        Long likedCafeid = cafeService.likedCafeAdd(cafe.getId());

        // then
        LikedCafe likedCafe = likedCafeRepository.findById(likedCafeid).get();

        assertThat(likedCafeid).isEqualTo(likedCafe.getId());

    }

    @Test
    public void 카페스크랩삭제() throws Exception{
        // given
        Cafe cafe = cafeRepository.findByName("테스트카페").get();
        Long likedCafeid = cafeService.likedCafeAdd(cafe.getId());

        //when
        cafeService.likedCafeDelete(cafe.getId(), 1L);

        // then
        Optional<LikedCafe> likedCafe = likedCafeRepository.findByCafeIdAndUserId(cafe.getId(), 1L);
        assertThat(likedCafe.isEmpty()).isEqualTo(true);
    }

    @Test
    public void 카페스크랩조회() throws Exception {
        // given
        Cafe cafe = cafeRepository.findByName("테스트카페").get();
        Long likedCafeid = cafeService.likedCafeAdd(cafe.getId());
    }
}
