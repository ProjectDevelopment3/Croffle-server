package com.sungshin.croffle.controller;

import com.sungshin.croffle.config.auth.UserPrincipal;
import com.sungshin.croffle.dto.Response;
import com.sungshin.croffle.dto.report.InfoReportDto;
import com.sungshin.croffle.dto.report.ReportCafeDto;
import com.sungshin.croffle.service.MenuService;
import com.sungshin.croffle.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;
    private final MenuService menuService;

    @PostMapping("/report/info")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Response info(Authentication authentication,
                         @RequestBody InfoReportDto reportDto) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        reportService.saveInfo(reportDto, userPrincipal.getId());
        return Response.builder()
                .code("201")
                .messages("오류 제보하기에 성공하였습니다.")
                .build();
    }

    @PostMapping("/report/cafe")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Response cafe(Authentication authentication, @RequestBody ReportCafeDto reportCafeDto) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        boolean diff = reportService.diffName(reportCafeDto.getCafeName());
        Long cafe_id = reportService.getCafeId(reportCafeDto.getCafeName());

        if (diff == false) {
            //등록된 카페가 없으면 카페 저장
            reportService.saveCafe(reportCafeDto);
            if (menuService.searchMenu(reportCafeDto) == true) {
                menuService.saveMenu(reportCafeDto, cafe_id);
                return Response.builder()
                        .code("200")
                        .messages("카페와 메뉴가 제보되었습니다.")
                        .build();
            } else {
                //카페만 등록
                reportService.saveCafe(reportCafeDto);
                return Response.builder()
                        .code("4000")
                        .messages("카페 제보에 실패 했습니다. : 메뉴가 제보 되지 않았습니다.")
                        .build();
            }

        }
        else{
            //등록된 카페가 있을 때
            if(menuService.searchMenu(reportCafeDto) == true) {
                menuService.saveMenu(reportCafeDto, cafe_id);
                return Response.builder()
                        .code("200")
                        .messages("메뉴가 제보되었습니다.")
                        .build();
            }

        }

        return Response.builder()
                .code("4000")
                .messages("이미 등록되어 있는 정보 입니다.")
                .build();

    }


    @PostMapping("/report/menu")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Response menu(Authentication authentication,
                         @RequestBody ReportCafeDto reportCafeDto) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Long cafe_id = reportService.getCafeId(reportCafeDto.getCafeName());
        menuService.saveMenu(reportCafeDto,cafe_id);
        return Response.builder()
                .code("201")
                .messages("메뉴 제보하기에 성공하였습니다.")
                .build();
    }

}