package com.example.animal.domain.animal.repository;

import com.example.animal.domain.animal.dto.request.AnimalSearchCondition;
import com.example.animal.domain.animal.entity.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnimalRepositoryCustom {

  Page<Animal> findAnimalByFilter(AnimalSearchCondition animalSearchCondition,
      Pageable pageable);

}
