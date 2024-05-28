package com.example.animal.domain.animal.service;

import com.example.animal.domain.animal.dto.response.AnimalResponse;
import com.example.animal.domain.animal.entity.Animal;
import com.example.animal.domain.enums.SexType;
import com.example.animal.domain.shelter.entity.Shelter;
import com.example.animal.domain.animal.dto.response.AnimalListOpenApiResponse;
import com.example.animal.domain.animal.dto.response.AnimalListResponse;
import com.example.animal.domain.animal.repository.AnimalRepository;
import com.example.animal.domain.shelter.repository.ShelterRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
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

    return animalRepository.saveAll(response.getAnimals().stream()
        .map((animal) -> animal.toEntity(shelter))
        .toList());
  }

  //보호소 동물 리스트 추출
  public List<AnimalListResponse> getAnimalList(Pageable pageable) {
    List<AnimalListResponse> pageResult = animalRepository.findAll(pageable)
        .stream()
        .map(AnimalListResponse::new)
        .toList();

    return pageResult;
  }

  //보호소 동물 상세 정보 조회
  public AnimalResponse getAnimalDetail(Long id) {
    Animal animal = animalRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Not Found Animal"));

    return new AnimalResponse(animal);
  }

  //보호소 동물 필터
  public List<AnimalListResponse> getFilteredAnimalList(String startYear, String endYear, SexType sexCd, LocalDate startDate, LocalDate endDate,Pageable pageable) {
    List<AnimalListResponse> pageResult = animalRepository.findByFilters(startYear, endYear, sexCd, startDate,endDate, pageable)
        .stream()
        .map(AnimalListResponse::new)
        .toList();

    return pageResult;
  }
}
