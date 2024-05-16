package com.example.animal.service;

import com.example.animal.domain.Animal;
import com.example.animal.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    //유기 동물 정보 저장
    public List<Animal> saveAll(List<Animal> animals) {
        return animalRepository.saveAll(animals);
    }

    //유기 동물 리스트 조회
    public List<Animal> findAll() {
        return animalRepository.findAll();
    }

}
