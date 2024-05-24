package com.example.animal.domain.animal.service;

import com.example.animal.domain.animal.entity.Animal;
import com.example.animal.domain.shelter.entity.Shelter;
import com.example.animal.domain.animal.dto.response.AnimalListResponse;
import com.example.animal.domain.animal.dto.response.AnimalListViewResponse;
import com.example.animal.domain.animal.repository.AnimalRepository;
import com.example.animal.domain.shelter.repository.ShelterRepository;
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
  public List<Animal> saveAll(AnimalListResponse response, String careRegNo) {
    Shelter shelter = shelterRepository.findByCareRegNo(careRegNo)
        .orElseThrow(() -> new IllegalArgumentException("Not Found Shelter"));

    return animalRepository.saveAll(response.getAnimals().stream()
        .map((animal) -> animal.toEntity(shelter))
        .toList());
  }

  //보호소 동물 리스트 추출
  public List<AnimalListViewResponse> getAnimalList(Pageable pageable) {
    List<AnimalListViewResponse> pageResult = animalRepository.findAll(pageable)
        .stream()
        .map(AnimalListViewResponse::new)
        .toList();

    return pageResult;
  }
}
