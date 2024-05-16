package com.example.animal.repository;

import com.example.animal.domain.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelterRepository extends JpaRepository<Shelter,Long> {
}
