package com.example.animal.domain.animal.dto.response;

import com.example.animal.domain.animal.entity.Animal;
import lombok.Builder;

@Builder
public record AnimalListResponse(
    Long id,
    String popFile,
    String specialMark,
    String animalBreed,
    String age,
    String sex,
    String neuterYn,
    String noticeSdt,
    String noticeEdt
) {

  public static AnimalListResponse fromEntity(Animal animal) {
    return AnimalListResponse.builder()
        .id(animal.getId())
        .specialMark(animal.getSpecialMark())
        .popFile(animal.getPopFile())
        .animalBreed(animal.getAnimalBreed())
        .age(animal.getAge())
        .sex(animal.getSexCd().label)
        .neuterYn(animal.getNeuterYn().label)
        .noticeSdt(animal.getNoticeSdt().toString())
        .noticeEdt(animal.getNoticeEdt().toString())
        .build();
  }
}
