package com.sungshin.croffle.service;

import com.sungshin.croffle.domain.dto.ReportDto;
import com.sungshin.croffle.domain.dto.ReviewDto;
import com.sungshin.croffle.domain.jpa.ReportRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;

    @Transactional
    public Long saveReport(ReportDto reportDto) {
        return reportRepository.save(reportDto.toEntity()).getId();
    }


}
