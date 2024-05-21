package com.example.animal.repository;

import com.example.animal.domain.Shelter;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelterRepository extends JpaRepository<Shelter, Long> {

  Optional<Shelter> findByCareRegNo(String careRegNo);

  Optional<List<Shelter>> findByDistrictId(Integer id);
}
