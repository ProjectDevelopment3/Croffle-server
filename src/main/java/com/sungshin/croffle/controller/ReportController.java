package com.sungshin.croffle.controller;


import com.sungshin.croffle.domain.dto.ReportDto;
import com.sungshin.croffle.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping("report/modify")
    public String modify(@RequestBody ReportDto reportDto) {
        reportService.saveReport(reportDto);
        //TO-DO 추후에 응답 메세지 반환으로 수정
        return "redirect:/";
    }

}
