package com.example.animal.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class District {
    //member
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DISTRICT_ID")
    private Long id;
    //시군구코드
    private String orgCd;
    //시군구명
    private String orgdownNm;

    @ManyToOne
    @JoinColumn(name = "CITYPROVINCE_ID")
    private CityProvince cityProvince;

    @OneToMany(mappedBy = "district")
    private List<Shelter> shelters = new ArrayList<>();


    public void setCityProvince(CityProvince cityProvince) {
        this.cityProvince = cityProvince;

        if (!cityProvince.getDistricts().contains(this)) {
            cityProvince.getDistricts().add(this);
        }
    }

    @Builder
    public District(String orgCd, String orgdownNm, CityProvince cityProvince) {
        this.cityProvince = cityProvince;
        this.orgCd = orgCd;
        this.orgdownNm = orgdownNm;
    }
}
