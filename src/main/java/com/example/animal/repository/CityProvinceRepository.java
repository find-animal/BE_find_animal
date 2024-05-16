package com.example.animal.repository;

import com.example.animal.domain.CityProvince;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityProvinceRepository extends JpaRepository<CityProvince, Long> {
    Optional<CityProvince> findByOrgCd(String orgCd);
}
