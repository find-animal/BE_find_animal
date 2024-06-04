package com.example.animal.domain.animal.service;

import com.example.animal.exception.RestApiException;
import com.example.animal.exception.common.CommonErrorCode;
import com.example.animal.exception.enums.ErrorCode;
import com.example.animal.domain.animal.dto.request.FilterAnimalRequest;
import com.example.animal.domain.animal.dto.response.AnimalListOpenApiResponse;
import com.example.animal.domain.animal.dto.response.AnimalListResponse;
import com.example.animal.domain.animal.dto.response.AnimalPageResponse;
import com.example.animal.domain.animal.dto.response.AnimalResponse;
import com.example.animal.domain.animal.entity.Animal;
import com.example.animal.domain.animal.repository.AnimalRepository;
import com.example.animal.domain.shelter.entity.Shelter;
import com.example.animal.domain.shelter.repository.ShelterRepository;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AnimalService {

  private final AnimalRepository animalRepository;
  private final ShelterRepository shelterRepository;

  //보호소 동물 저장
  public List<Animal> saveAll(AnimalListOpenApiResponse response, String careRegNo) {
    Shelter shelter = shelterRepository.findByCareRegNo(careRegNo)
        .orElseThrow(() -> new IllegalArgumentException("Not Found Shelter"));

    if(response == null) {
      return Collections.emptyList();
    }

    return animalRepository.saveAll(response.getAnimals().stream()
        .map((animalResponse) -> animalResponse.toEntity(shelter))
        .toList());
  }

  //보호소 동물 상세 정보 조회
  public AnimalResponse getAnimalDetail(Long id) {
    Animal animal = animalRepository.findById(id)
        .orElseThrow(() -> new RestApiException(CommonErrorCode.NO_MATCHING_RESOURCE));

    return AnimalResponse.fromEntity(animal);
  }

  //보호소 동물 필터
  public AnimalPageResponse getFilteredAnimalList(FilterAnimalRequest filterAnimalRequest,
      Pageable pageable) {
    Page<Animal> animalPage = animalRepository.findAnimalByFilter(filterAnimalRequest, pageable);
    List<AnimalListResponse> animals = animalPage.getContent().stream()
        .map((animal -> AnimalListResponse.fromEntity(animal)))
        .toList();

    return AnimalPageResponse.of(animals, animalPage);
  }
}
