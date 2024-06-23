package com.example.animal.domain.shelter.repository;

import com.example.animal.domain.shelter.entity.Shelter;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelterRepository extends JpaRepository<Shelter, Long>, ShelterRepositoryCustom {

  Optional<Shelter> findByCareRegNo(String careRegNo);

  Optional<List<Shelter>> findByDistrictId(Long id);

  Optional<List<Shelter>> findByCityProvinceId(Long id);


}
