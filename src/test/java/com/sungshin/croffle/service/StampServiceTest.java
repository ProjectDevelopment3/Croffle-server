package com.sungshin.croffle.service;

import com.sungshin.croffle.domain.jpa.StampRepository;
import com.sungshin.croffle.dto.stamp.StampListDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class StampServiceTest {

    @Autowired
    StampService stampService;
    @Autowired
    StampRepository stampRepository;

    @BeforeEach
    public void beforeEach() {

    }

    @Test
    void 스탬프_조회하기() {
        // given
        List<StampListDto> stampListDtos = stampRepository.findStampAndCafeByUserId(1L);
        System.out.println(stampListDtos.size());
        // when
        int cnt = stampService.stampList(1L).size();

        // then
        assertThat(cnt).isEqualTo(stampListDtos.size());
    }
}
