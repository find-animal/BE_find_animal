package com.example.animal.domain.animal.repository;

import com.example.animal.domain.animal.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long>,
    AnimalRepositoryCustom {

}
