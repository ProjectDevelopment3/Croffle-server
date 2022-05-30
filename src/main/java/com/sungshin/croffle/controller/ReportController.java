package com.sungshin.croffle.controller;

import com.sungshin.croffle.config.auth.UserPrincipal;
import com.sungshin.croffle.dto.Response;
import com.sungshin.croffle.dto.report.InfoReportDto;
import com.sungshin.croffle.dto.report.ReportCafeDto;
import com.sungshin.croffle.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping("/report/info")
    public Response info(Authentication authentication,
                         @RequestBody InfoReportDto reportDto) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        reportService.saveInfo(reportDto);
        return Response.builder()
                .code("201")
                .messages("오류 제보하기에 성공하였습니다.")
                .build();
    }

    @PostMapping("/report/menu")
    public Response menu(Authentication authentication,
                         @RequestBody ReportCafeDto reportCafeDto) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        reportService.saveMenu(reportCafeDto);
        return Response.builder()
                .code("201")
                .messages("메뉴 제보하기에 성공하였습니다.")
                .build();
    }

}