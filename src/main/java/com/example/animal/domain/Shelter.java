package com.example.animal.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Shelter {
    //member

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    //보호소 번호
    private String careRegNo;
    //보호소명
    private String careNm;
    //시도명
    @ManyToOne
    @JoinColumn(name = "CITYPROVINCE_ID")
    private CityProvince cityProvince;

    public void setCityProvince(CityProvince cityProvince) {
        this.cityProvince = cityProvince;

        if (!cityProvince.getShelters().contains(this)) {
            cityProvince.getShelters().add(this);
        }
    }

    @Builder
    public Shelter(String careRegNo, String careNm, CityProvince cityProvince) {
        this.careRegNo = careRegNo;
        this.careNm = careNm;
        this.cityProvince = cityProvince;
    }

}
