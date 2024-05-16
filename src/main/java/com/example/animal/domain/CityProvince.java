package com.example.animal.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class CityProvince {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CITYPROVINCE_ID")
    private Long id;
    //시도 코드
    private String orgCd;
    //시도명
    private String orgdownNm;

    @Builder
    public CityProvince(String orgCd, String orgdownNm) {
        this.orgCd = orgCd;
        this.orgdownNm = orgdownNm;
    }
}
