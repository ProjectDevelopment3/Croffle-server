package com.sungshin.croffle.service;

import com.sungshin.croffle.dto.report.ReportDto;
import com.sungshin.croffle.domain.jpa.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;

    @Transactional
    public Long saveReport(ReportDto reportDto) {
        return reportRepository.save(reportDto.toEntity()).getId();
    }


}
