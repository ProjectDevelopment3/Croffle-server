package com.sungshin.croffle.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long cafeId;

    @Column(length = 40, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String price;

    private int checked;

    @Builder
    public Menu(Long cafe_id, String name, String price, int checked){
        this.cafeId = cafe_id;
        this.name = name;
        this.price = price;
        this.checked = checked;
    }

    public void update(String name, String price){
        this.name = name;
        this.price = price;
    }



}
