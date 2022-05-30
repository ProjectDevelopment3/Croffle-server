package com.sungshin.croffle.service;

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
}
