package com.sungshin.croffle.controller;

import com.sungshin.croffle.config.auth.UserPrincipal;
import com.sungshin.croffle.config.auth.dto.CurrentUser;
import com.sungshin.croffle.dto.Response;
import com.sungshin.croffle.dto.report.InfoReportDto;
import com.sungshin.croffle.dto.report.ReportCafeDto;
import com.sungshin.croffle.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping("/report/info")
    @PreAuthorize("hasRole('USER')")
    public Response info(@CurrentUser UserPrincipal userPrincipal,
                         @RequestBody InfoReportDto reportDto) {
        reportService.saveInfo(reportDto);
        return Response.builder()
                .code("201")
                .messages("오류 제보하기에 성공하였습니다.")
                .build();
    }

    @PostMapping("/report/menu")
    @PreAuthorize("hasRole('USER')")
    public Response menu(@CurrentUser UserPrincipal userPrincipal,
                         @RequestBody ReportCafeDto reportCafeDto) {
        reportService.saveMenu(reportCafeDto);
        return Response.builder()
                .code("201")
                .messages("메뉴 제보하기에 성공하였습니다.")
                .build();
    }

}