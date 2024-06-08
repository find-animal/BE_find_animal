package com.example.animal.domain.animal.dto.response;

import com.example.animal.domain.animal.entity.Animal;
import java.util.Objects;
import lombok.Builder;

@Builder
public record AnimalListResponse(
    Long id,
    String popFile,
    String animalBreed,
    String age,
    String sex,
    String neuterYn,
    String noticeSdt,
    String noticeEdt
) {

  public AnimalListResponse {
    Objects.requireNonNull(id, "id must not be null");
    Objects.requireNonNull(popFile, "popFile must not be null");
    Objects.requireNonNull(animalBreed, "animalBreed must not be null");
    Objects.requireNonNull(age, "age must not be null");
    Objects.requireNonNull(sex, "sex must not be null");
    Objects.requireNonNull(neuterYn, "neuterYn must not be null");
    Objects.requireNonNull(noticeSdt, "noticeSdt must not be null");
    Objects.requireNonNull(noticeEdt, "noticeEdt must not be null");
  }

  public static AnimalListResponse fromEntity(Animal animal) {
    return AnimalListResponse.builder()
        .id(animal.getId())
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
