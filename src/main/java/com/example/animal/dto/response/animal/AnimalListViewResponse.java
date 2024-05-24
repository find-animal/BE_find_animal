package com.example.animal.dto.response.animal;

import com.example.animal.domain.Animal;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class AnimalListViewResponse {
  private Long id;
  private String fileName;
  private String kindCd;
  private String happenPlace;
  private LocalDate happenDt;
  private String age;
  private String weight;
  private LocalDate noticeSdt;
  private LocalDate noticeEdt;

  public AnimalListViewResponse(Animal animal) {
    this.id = animal.getId();
    this.fileName = animal.getFileName();
    this.kindCd = animal.getKindCd();
    this.happenPlace = animal.getHappenPlace();
    this.happenDt = animal.getHappenDt();
    this.age = animal.getAge();
    this.weight = animal.getWeight();
    this.noticeSdt = animal.getNoticeSdt();
    this.noticeEdt = animal.getNoticeEdt();
  }

}
