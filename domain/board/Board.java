package com.sungshin.croffle.domain.board;

import com.sungshin.croffle.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity

public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long user_id;

    @Column(length = 30, nullable = false)
    private String title;

    @Column(length=1500, nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    private BoardCategory boardCategory;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;



    @Builder
    public Board(Long id, Long user_id, String title, String content,BoardCategory boardCategory){
        this.id = id;
        this.user_id = user_id;
        this.title = title;
        this.content = content;
        this.boardCategory = boardCategory;

    }

    public void update(String title, String content,BoardCategory boardCategory, LocalDateTime modifiedDate){
        this.title = title;
        this.content = content;
        this.boardCategory = boardCategory;
        this.modifiedDate = modifiedDate;
    }

}
