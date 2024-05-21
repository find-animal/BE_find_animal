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
    @Column(name = "id")
    private Long id;
    //유기번호
    private String desertionNo;
    //접수일
    @Temporal(TemporalType.DATE)
    private LocalDate happenDt;
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
    //보호소 이름
    private String careNm;
    //보호소 전화번호
    private String careTel;
    //보호 장소
    private String careAddr;
    //관할기관
    private String orgNm;
    //담당자
    private String chargeNm;
    //담당자 연락처
    private String officeTel;

    @Builder
    public Animal(String desertionNo, LocalDate happenDt, String happenPlace, String kindCd, String colorCd, String age, String weight, String fileName, String popFile, SexType sexCd, NeuterType neuterYn, String specialMark, String careNm, String careTel, String careAddr, String orgNm, String chargeNm, String officeTel) {
        this.desertionNo = desertionNo;
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
        this.careNm = careNm;
        this.careTel = careTel;
        this.careAddr = careAddr;
        this.orgNm = orgNm;
        this.chargeNm = chargeNm;
        this.officeTel = officeTel;
    }
}
