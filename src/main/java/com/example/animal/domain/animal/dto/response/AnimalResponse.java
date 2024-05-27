package com.example.animal.domain.animal.dto.response;

import com.example.animal.domain.animal.entity.Animal;
import com.example.animal.domain.enums.NeuterType;
import com.example.animal.domain.enums.SexType;
import com.example.animal.domain.shelter.dto.response.ShelterResponse;
import lombok.Getter;

@Getter
public class AnimalResponse {

  private String popFile;
  private String specialMark;
  private String age;
  private String colorCd;
  private NeuterType neuterYn;
  private SexType sexCd;
  private String weight;
  private String happenPlace;
  private String animalBreed;
  private ShelterResponse shelter;

  public AnimalResponse(Animal animal) {
    this.popFile = animal.getPopFile();
    this.specialMark = animal.getSpecialMark();
    this.age = animal.getAge();
    this.colorCd = animal.getColorCd();
    this.neuterYn = animal.getNeuterYn();
    this.sexCd = animal.getSexCd();
    this.weight = animal.getWeight();
    this.happenPlace = animal.getHappenPlace();
    this.animalBreed = animal.getAnimalBreed();
    this.shelter = new ShelterResponse(animal.getShelter());
  }

}
