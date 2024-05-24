package com.example.animal.service;

import com.example.animal.domain.Animal;
import com.example.animal.domain.Shelter;
import com.example.animal.dto.response.animal.AnimalListResponse;
import com.example.animal.repository.AnimalRepository;
import com.example.animal.repository.ShelterRepository;
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
  public List<Animal> saveAll(AnimalListResponse response, String careRegNo) {
    Shelter shelter = shelterRepository.findByCareRegNo(careRegNo)
        .orElseThrow(() -> new IllegalArgumentException("Not Found Shelter"));

    return animalRepository.saveAll(response.getAnimals().stream()
        .map((animal) -> animal.toEntity(shelter))
        .toList());
  }

  //보호소 동물 리스트 추출
  public Page<Animal> getAnimalList(Pageable pageable) {
    Page<Animal> pageResult = animalRepository.findAll(pageable);

    return pageResult;
  }
}
