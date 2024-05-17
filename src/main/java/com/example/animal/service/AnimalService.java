package com.example.animal.service;

import com.example.animal.domain.Animal;
import com.example.animal.dto.response.animal.AnimalListResponse;
import com.example.animal.dto.response.animal.AnimalResponse;
import com.example.animal.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AnimalService {
    private final AnimalRepository animalRepository;

    //보호소 동물 저장
    public List<Animal> saveAll(AnimalListResponse response) {
        return animalRepository.saveAll(response.getAnimals().stream()
                .map(AnimalResponse::toEntity)
                .toList());
    }
}
