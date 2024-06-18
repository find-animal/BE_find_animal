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
