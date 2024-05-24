package com.example.animal.dto.response.animal;

import com.example.animal.domain.Animal;
import com.example.animal.domain.enums.NeuterType;
import com.example.animal.domain.enums.SexType;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class AnimalListViewResponse {

  private Long id;
  private String kindCd;
  private String age;
  private SexType sexCd;
  private NeuterType neuterYn;
  private LocalDate noticeSdt;

  public AnimalListViewResponse(Animal animal) {
    this.id = animal.getId();
    this.kindCd = animal.getKindCd();
    this.age = animal.getAge();
    this.sexCd = animal.getSexCd();
    this.neuterYn = animal.getNeuterYn();
    this.noticeSdt = animal.getNoticeSdt();
  }
}
