package com.sungshin.croffle.domain.board;

import com.sungshin.croffle.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Column(length = 30, nullable = false)
    private String title;

    @Column(length=1500, nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    private BoardCategory boardCategory;



    @Builder
    public Board(Long id, Long user_id, String title, String content, BoardCategory boardCategory){
        this.id = id;
        this.userId = user_id;
        this.title = title;
        this.content = content;
        this.boardCategory = boardCategory;

    }

    public void update(String title, String content,BoardCategory boardCategory){
        this.title = title;
        this.content = content;
        this.boardCategory = boardCategory;
    }

}
