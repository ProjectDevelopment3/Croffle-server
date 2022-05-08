package com.sungshin.croffle.domain.jpa;

import com.sungshin.croffle.domain.report.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {

}
