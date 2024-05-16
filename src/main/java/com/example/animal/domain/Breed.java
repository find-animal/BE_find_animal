package com.example.animal.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Breed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    //품종 코드
    private String kindCd;

    //품종명
    @Column(name = "knm")
    private String knm;

    @Builder
    public Breed(String kindCd, String knm) {
        this.kindCd = kindCd;
        this.knm = knm;
    }

}
