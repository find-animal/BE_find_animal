package com.example.animal.domain.animal.dto.response;

import com.example.animal.domain.animal.entity.Animal;
import java.time.LocalDate;
import java.util.Objects;
import lombok.Builder;

@Builder
public record AnimalListResponse(
    Long id,
    String fileName,
    String animalBreed,
    String happenPlace,
    LocalDate happenDt,
    String age,
    String sex,
    String weight,
    LocalDate noticeSdt,
    LocalDate noticeEdt
) {

  public AnimalListResponse {
    Objects.requireNonNull(id, "id must not be null");
    Objects.requireNonNull(fileName, "fileName must not be null");
    Objects.requireNonNull(animalBreed, "animalBreed must not be null");
    Objects.requireNonNull(happenPlace, "happenPlace must not be null");
    Objects.requireNonNull(happenDt, "happenDt must not be null");
    Objects.requireNonNull(age, "age must not be null");
    Objects.requireNonNull(sex, "sex must not be null");
    Objects.requireNonNull(weight, "weight must not be null");
    Objects.requireNonNull(noticeSdt, "noticeSdt must not be null");
    Objects.requireNonNull(noticeEdt, "noticeEdt must not be null");
  }

  public static AnimalListResponse fromEntity(Animal animal) {
    return AnimalListResponse.builder()
        .id(animal.getId())
        .fileName(animal.getFileName())
        .animalBreed(animal.getAnimalBreed())
        .happenPlace(animal.getHappenPlace())
        .happenDt(animal.getHappenDt())
        .age(animal.getAge())
        .sex(animal.getSexCd().label)
        .weight(animal.getWeight())
        .noticeSdt(animal.getNoticeSdt())
        .noticeEdt(animal.getNoticeEdt())
        .build();
  }
}
