package com.sungshin.croffle.dto.report;

import com.sungshin.croffle.domain.Report;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InfoReportDto {
    private Long cafeId;
    private String content;
    private String category;


    public Report toEntity(Long userId) {
        return Report.builder()
                .user_id(userId)
                .cafe_id(cafeId)
                .content(content)
                .category(category)
                .build();
    }

    @Builder
    public InfoReportDto(Long cafe_id, String content, String category){
        this.cafeId = cafe_id;
        this.content = content;
        this.category = category;
    }

}