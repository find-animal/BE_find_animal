package com.example.animal.dto.response.animal;

import com.example.animal.domain.Animal;
import com.example.animal.domain.Shelter;
import com.example.animal.domain.enums.NeuterType;
import com.example.animal.domain.enums.SexType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnimalResponse {

  //접수일
  @JsonProperty("happenDt")
  @JsonSerialize(using = LocalDateSerializer.class)
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate happenDt;
  //공고시작일
  @JsonProperty("noticeSdt")
  @JsonSerialize(using = LocalDateSerializer.class)
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate noticeSdt;
  //공고종료일
  @JsonProperty("noticeEdt")
  @JsonSerialize(using = LocalDateSerializer.class)
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate noticeEdt;
  //발견장소
  @JsonProperty("happenPlace")
  private String happenPlace;
  //품종
  @JsonProperty("kindCd")
  private String kindCd;
  //색상
  @JsonProperty("colorCd")
  private String colorCd;
  //나이(년생)
  @JsonProperty("age")
  private String age;
  //체중(kg)
  @JsonProperty("weight")
  private String weight;
  //썸네일 이미지 url
  @JsonProperty("filename")
  private String fileName;
  //이미지
  @JsonProperty("popfile")
  private String popFile;
  //성별
  @JsonProperty("sexCd")
  private SexType sexCd;
  //중성화 여부
  @JsonProperty("neuterYn")
  private NeuterType neuterYn;
  //특징
  @JsonProperty("specialMark")
  private String specialMark;
  //보호소 이름
  @JsonProperty("careNm")
  private String careNm;
  //보호소 전화번호
  @JsonProperty("careTel")
  private String careTel;
  //보호 장소
  @JsonProperty("careAddr")
  private String careAddr;
  //관할기관
  @JsonProperty("orgNm")
  private String orgNm;
  //담당자
  @JsonProperty("chargeNm")
  private String chargeNm;
  //담당자 연락처
  @JsonProperty("officetel")
  private String officeTel;

  public Animal toEntity(Shelter shelter) {
    return Animal.builder()
        .happenDt(this.happenDt)
        .happenPlace(this.happenPlace)
        .kindCd(this.kindCd)
        .colorCd(this.colorCd)
        .age(this.age)
        .weight(this.weight)
        .fileName(this.fileName)
        .popFile(this.popFile)
        .sexCd(this.sexCd)
        .neuterYn(this.neuterYn)
        .specialMark(this.specialMark)
        .careNm(this.careNm)
        .careTel(this.careTel)
        .careAddr(this.careAddr)
        .orgNm(this.orgNm)
        .chargeNm(this.chargeNm)
        .officeTel(this.officeTel)
        .noticeSdt(this.noticeSdt)
        .noticeEdt(this.noticeEdt)
        .shelter(shelter)
        .build();
  }
}
