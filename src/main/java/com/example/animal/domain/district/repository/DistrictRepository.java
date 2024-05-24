package com.example.animal.domain.district.repository;

import com.example.animal.domain.district.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DistrictRepository extends JpaRepository<District, Long> {
    Optional<District> findByOrgCd(String orgCd);
}
