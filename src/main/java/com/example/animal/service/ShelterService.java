package com.example.animal.service;

import com.example.animal.domain.CityProvince;
import com.example.animal.domain.Shelter;
import com.example.animal.dto.response.shelter.ShelterListResponse;
import com.example.animal.repository.CityProvinceRepository;
import com.example.animal.repository.ShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ShelterService {
    private final ShelterRepository shelterRepository;
    private final CityProvinceRepository cityProvinceRepository;

    //보호소 전체 정보 저장
    public List<Shelter> saveAll(ShelterListResponse response, String uprCd) {
        CityProvince cityProvince = cityProvinceRepository.findByOrgCd(uprCd)
                .orElseThrow(() -> new IllegalArgumentException("Not Found cityProvince"));

        return shelterRepository.saveAll(response.getShelters().stream()
                .map((shelter) -> shelter.toEntity(cityProvince))
                .toList());
    }
}
