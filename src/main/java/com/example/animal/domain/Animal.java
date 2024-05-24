package com.example.animal.domain;

import com.example.animal.domain.enums.NeuterType;
import com.example.animal.domain.enums.SexType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANIMAL_ID")
    private Long id;
    //접수일
    @Temporal(TemporalType.DATE)
    private LocalDate happenDt;
    //공고시작일
    @Temporal(TemporalType.DATE)
    private LocalDate noticeSdt;
    //공고종료일
    @Temporal(TemporalType.DATE)
    private LocalDate noticeEdt;
    //발견장소
    private String happenPlace;
    //품종
    private String kindCd;
    //색상
    private String colorCd;
    //나이(년생)
    private String age;
    //체중(kg)
    private String weight;
    //썸네일 이미지 url
    private String fileName;
    //이미지
    private String popFile;
    //성별
    @Enumerated(value = EnumType.STRING)
    private SexType sexCd;
    //중성화 여부
    @Enumerated(value = EnumType.STRING)
    private NeuterType neuterYn;
    //특징
    @Lob
    private String specialMark;

    @ManyToOne
    @JoinColumn(name = "SHELTER_ID")
    private Shelter shelter;

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;

        if (!shelter.getAnimals().contains(this)) {
            shelter.getAnimals().add(this);
        }
    }

    @Builder
    public Animal(LocalDate happenDt, String happenPlace, String kindCd, String colorCd, String age, String weight, String fileName, String popFile, SexType sexCd, NeuterType neuterYn, String specialMark, String careNm, String careTel, String careAddr, String orgNm, String chargeNm, String officeTel, LocalDate noticeSdt, LocalDate noticeEdt, Shelter shelter) {
        this.happenDt = happenDt;
        this.happenPlace = happenPlace;
        this.kindCd = kindCd;
        this.colorCd = colorCd;
        this.age = age;
        this.weight = weight;
        this.fileName = fileName;
        this.popFile = popFile;
        this.sexCd = sexCd;
        this.neuterYn = neuterYn;
        this.specialMark = specialMark;
        this.noticeSdt = noticeSdt;
        this.noticeEdt = noticeEdt;
        if(shelter.getCareAddr() == null) {
            shelter.setCareAddr(careAddr);
            shelter.setCareTel(careTel);
            shelter.setOrgNm(orgNm);
            shelter.setChargeNm(chargeNm);
            shelter.setOfficeTel(officeTel);
        }
        this.shelter = shelter;
    }
}
