package com.sungshin.croffle.service;

import com.sungshin.croffle.domain.Stamp;
import com.sungshin.croffle.domain.jpa.StampRepository;
import com.sungshin.croffle.dto.stamp.StampListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StampService {

    private final StampRepository stampRepository;

    public List<StampListDto> stampList(Long userId) {
        return stampRepository.findStampAndCafeByUserId(userId);
    }

    public void addStamp(Long cafeId, Long userId) {
        Stamp stamp = stampRepository.findByUserIdAndCafeId(userId, cafeId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 user 또는 cafe Id 입니다."));
        if (stamp.getStampCnt() < 9) {
            stamp.addStampCnt();
        } else {
            // stamp 개수 초기화, 새로운 coupon 추가
            stampRepository.delete(stamp);
            //coupon repository 에 쿠폰 추가
        }
    }
}
