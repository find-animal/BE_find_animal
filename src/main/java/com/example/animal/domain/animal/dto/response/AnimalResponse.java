package com.example.animal.domain.animal.dto.response;

import com.example.animal.domain.animal.entity.Animal;
import com.example.animal.domain.shelter.dto.response.ShelterResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AnimalResponse {

  private String popFile;
  private String specialMark;
  private String age;
  private String colorCd;
  private String neuterYn;
  private String sex;
  private String weight;
  private String happenPlace;
  private String animalBreed;
  private ShelterResponse shelter;

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
