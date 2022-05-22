package com.sungshin.croffle.dto.report;

import com.sungshin.croffle.domain.Report;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InfoReportDto {
    private Long userId;
    private Long cafeId;
    private String content;
    private String category;


    public Report toEntity() {
        return Report.builder()
                .user_id(userId)
                .cafe_id(cafeId)
                .content(content)
                .category(category)
                .build();
    }



    @Builder
    public InfoReportDto(Long user_id, Long cafe_id, String content, String category){
        this.userId = user_id;
        this.cafeId = cafe_id;
        this.content = content;
        this.category = category;
    }



}