package com.example.animal.domain.animal.dto.response;

import com.example.animal.domain.animal.entity.Animal;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class AnimalListViewResponse {
  private Long id;
  private String fileName;
  private String animalBreed;
  private String happenPlace;
  private LocalDate happenDt;
  private String age;
  private String weight;
  private LocalDate noticeSdt;
  private LocalDate noticeEdt;

  public AnimalListViewResponse(Animal animal) {
    this.id = animal.getId();
    this.fileName = animal.getFileName();
    this.animalBreed = animal.getAnimalBreed();
    this.happenPlace = animal.getHappenPlace();
    this.happenDt = animal.getHappenDt();
    this.age = animal.getAge();
    this.weight = animal.getWeight();
    this.noticeSdt = animal.getNoticeSdt();
    this.noticeEdt = animal.getNoticeEdt();
  }

}
