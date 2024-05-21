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
    @JoinColumn(name = "DISTRICT_ID")
    private District district;

    public void setDistrict(District district) {
        this.district = district;

        if (!district.getShelters().contains(this)) {
            district.getShelters().add(this);
        }
    }

    @Builder
    public Shelter(String careRegNo, String careNm, District district) {
        this.careRegNo = careRegNo;
        this.careNm = careNm;
        this.district = district;
    }

}
