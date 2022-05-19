package com.sungshin.croffle.dto.report;

import com.sungshin.croffle.domain.Report;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InfoReportDto {
    private Long user_id;
    private Long cafe_id;
    private String content;
    private String category;


    public Report toEntity() {
        return Report.builder()
                .user_id(user_id)
                .cafe_id(cafe_id)
                .content(content)
                .category(category)
                .build();
    }



    @Builder
    public InfoReportDto(Long user_id, Long cafe_id, String content, String category){
        this.user_id = user_id;
        this.cafe_id = cafe_id;
        this.content = content;
        this.category = category;
    }



}