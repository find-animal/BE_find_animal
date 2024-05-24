package com.example.animal.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class Shelter {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "SHELTER_ID")
  private Long id;
  //보호소 번호
  private String careRegNo;
  //보호소명
  private String careNm;
  //보호소 주소
  private String careAddr;
  //보호소 전화번호
  private String careTel;
  //관할기관
  private String orgNm;
  //담당자 이름
  private String chargeNm;
  //담당자 연락처
  private String officeTel;
  //시도명
  @ManyToOne
  @JoinColumn(name = "DISTRICT_ID")
  private District district;

  @OneToMany(mappedBy = "shelter")
  private List<Animal> animals = new ArrayList<>();

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
