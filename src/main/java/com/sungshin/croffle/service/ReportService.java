package com.sungshin.croffle.service;

import com.sungshin.croffle.domain.jpa.CafeRepository;
import com.sungshin.croffle.domain.jpa.MenuRepository;
import com.sungshin.croffle.dto.report.InfoReportDto;
import com.sungshin.croffle.dto.report.ReportCafeDto;
import com.sungshin.croffle.domain.jpa.ReportRepository;
import com.sungshin.croffle.dto.report.ReportMenuDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
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
        if (cafeRepository.findByName(cafeName) != null) {
            return true;
        }
        return false;
    }

    @Transactional
    public Long getCafeId(String cafeName) {
        return cafeRepository.findByName(cafeName).orElseThrow(()->new IllegalArgumentException("해당하는 카페가 없습니다.")).getId();
    }


}