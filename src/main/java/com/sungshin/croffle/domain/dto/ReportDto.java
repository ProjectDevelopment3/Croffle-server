package com.sungshin.croffle.domain.dto;
import com.sungshin.croffle.domain.report.Report;
import lombok.Builder;

public class ReportDto {
    private Long user_id;
    private Long cafe_id;
    private String content;
    private String category;

    //enum type 어떻게 해결할지.

    public Report toEntity() {
        return Report.builder()
                .user_id(user_id)
                .cafe_id(cafe_id)
                .content(content)
                .category(category)
                .build();
    }



    @Builder
    public ReportDto(Long user_id, Long cafe_id, String content, String category){
        this.user_id = user_id;
        this.cafe_id = cafe_id;
        this.content = content;
        this.category = category;
    }



}

