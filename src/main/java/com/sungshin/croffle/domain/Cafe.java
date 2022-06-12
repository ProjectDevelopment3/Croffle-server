package com.sungshin.croffle.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Cafe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40, nullable = false)
    private String name;

    @Column(length = 30)
    private String telephone;

    @Column(columnDefinition = "TINYINT(1)")
    @ColumnDefault("0")
    private boolean checked;

    @Column(length = 200)
    private String addr;

    private String hours;
    private String site;
    private String benefit;

    @Builder
    public Cafe(String name, String addr) {
        this.name = name;
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "Cafe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", checked=" + checked +
                ", addr='" + addr + '\'' +
                ", hours='" + hours + '\'' +
                ", site='" + site + '\'' +
                ", benefit='" + benefit + '\'' +
                '}';
    }
}
