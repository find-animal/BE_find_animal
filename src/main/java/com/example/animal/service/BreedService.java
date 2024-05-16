package com.example.animal.service;

import com.example.animal.domain.Breed;
import com.example.animal.dto.response.breed.BreadResponse;
import com.example.animal.dto.response.breed.BreedsListResponse;
import com.example.animal.repository.BreedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BreedService {
    private final BreedRepository breedRepository;

    //품종 전체 정보 저장
    public List<Breed> saveAll(BreedsListResponse response) {
        return breedRepository.saveAll(response.getAnimals().stream()
                .map(BreadResponse::toEntity)
                .toList());
    }
}
