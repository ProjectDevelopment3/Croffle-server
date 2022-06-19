package com.sungshin.croffle.service;

import com.sungshin.croffle.domain.Cafe;
import com.sungshin.croffle.domain.jpa.CafeRepository;
import com.sungshin.croffle.domain.jpa.MenuRepository;
import com.sungshin.croffle.dto.report.InfoReportDto;
import com.sungshin.croffle.dto.report.ReportCafeDto;
import com.sungshin.croffle.domain.jpa.ReportRepository;
import com.sungshin.croffle.dto.report.ReportMenuDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportService {
    private final ReportRepository reportRepository;
    private final MenuRepository menuRepository;
    private final CafeRepository cafeRepository;

    @Transactional
    public Long saveInfo(InfoReportDto reportDto, Long userId) {
        return reportRepository.save(reportDto.toEntity(userId)).getId();
    }

    @Transactional
    public Long saveCafe(ReportCafeDto reportCafeDto) {
        return cafeRepository.save(reportCafeDto.toEntity()).getId();
    }

    @Transactional
    public boolean diffName(String cafeName) {
        if(cafeRepository.findByName(cafeName).isPresent()){
            return true;
        }
        return false;
    }

    @Transactional
    public Long getCafeId(String cafeName) {
        log.info("getCafeId: " + cafeName);
        Optional<Cafe> entity = cafeRepository.findByName(cafeName);
        if (entity.isEmpty()) {
            return 0L;
        }
        return cafeRepository.findByName(cafeName).orElseThrow(()->new IllegalArgumentException("해당하는 카페가 없습니다.")).getId();
    }


}