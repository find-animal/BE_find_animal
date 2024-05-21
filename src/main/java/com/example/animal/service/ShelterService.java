package com.example.animal.service;

import com.example.animal.domain.District;
import com.example.animal.domain.Shelter;
import com.example.animal.dto.response.shelter.ShelterListResponse;
import com.example.animal.repository.DistrictRepository;
import com.example.animal.repository.ShelterRepository;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ShelterService {
    private final ShelterRepository shelterRepository;
    private final DistrictRepository districtRepository;

    //보호소 전체 정보 저장
    public List<Shelter> saveAll(ShelterListResponse response, String orgCd) {
        District district = districtRepository.findByOrgCd(orgCd)
                .orElseThrow(() -> new IllegalArgumentException("Not Found cityProvince"));

        return shelterRepository.saveAll(response.getShelters().stream()
                .map((shelter) -> shelter.toEntity(district))
                .toList());
    }

    public List<Shelter> findByDistrictId(int id) {
        return shelterRepository.findByDistrictId(id).orElse(Collections.emptyList());
    }
}
