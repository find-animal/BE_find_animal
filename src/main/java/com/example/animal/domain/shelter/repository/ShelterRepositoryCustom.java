package com.example.animal.domain.shelter.repository;

import com.example.animal.domain.shelter.dto.request.ShelterSearchCondition;
import com.example.animal.domain.shelter.entity.Shelter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ShelterRepositoryCustom {

  Page<Shelter> findShelterByFilter(ShelterSearchCondition shelterSearchCondition,
      Pageable pageable);
}
