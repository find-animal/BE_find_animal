package com.example.animal.domain.animal.dto.response;

import com.example.animal.domain.animal.entity.Animal;
import com.example.animal.domain.enums.SexType;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AnimalListResponse {

  private Long id;
  private String fileName;
  private String animalBreed;
  private String happenPlace;
  private LocalDate happenDt;
  private String age;
  private SexType sexCd;
  private String weight;
  private LocalDate noticeSdt;
  private LocalDate noticeEdt;

  public static AnimalListResponse fromEntity(Animal animal) {
    return AnimalListResponse.builder()
        .id(animal.getId())
        .fileName(animal.getFileName())
        .animalBreed(animal.getAnimalBreed())
        .happenPlace(animal.getHappenPlace())
        .happenDt(animal.getHappenDt())
        .age(animal.getAge())
        .sexCd(animal.getSexCd())
        .weight(animal.getWeight())
        .noticeSdt(animal.getNoticeSdt())
        .noticeEdt(animal.getNoticeEdt())
        .build();
  }
}
