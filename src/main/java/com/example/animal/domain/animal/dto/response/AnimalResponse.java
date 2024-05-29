package com.example.animal.domain.animal.dto.response;

import com.example.animal.domain.animal.entity.Animal;
import com.example.animal.domain.shelter.dto.response.ShelterResponse;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;
import lombok.Builder;

@Builder
public record AnimalResponse(
    String popFile,
    String specialMark,
    String age,
    String colorCd,
    String neuterYn,
    String sex,
    String weight,
    String happenPlace,
    String animalBreed,
    ShelterResponse shelter
) {
  public AnimalResponse {
    Objects.requireNonNull(popFile, "popFile must not be null");
    Objects.requireNonNull(specialMark, "specialMark must not be null");
    Objects.requireNonNull(age, "age must not be null");
    Objects.requireNonNull(neuterYn, "neuterYn must not be null");
    Objects.requireNonNull(sex, "sex must not be null");
    Objects.requireNonNull(weight, "weight must not be null");
    Objects.requireNonNull(happenPlace, "happenPlace must not be null");
    Objects.requireNonNull(animalBreed, "animalBreed must not be null");
    Objects.requireNonNull(shelter, "shelter must not be null");
  }

  public static AnimalResponse fromEntity(Animal animal) {
    return AnimalResponse.builder()
        .popFile(animal.getPopFile())
        .specialMark(animal.getSpecialMark())
        .age(animal.getAge())
        .colorCd(animal.getColorCd())
        .neuterYn(animal.getNeuterYn().label)
        .sex(animal.getSexCd().label)
        .weight(animal.getWeight())
        .happenPlace(animal.getHappenPlace())
        .animalBreed(animal.getAnimalBreed())
        .shelter(ShelterResponse.fromEntity(animal.getShelter()))
        .build();
  }
}
