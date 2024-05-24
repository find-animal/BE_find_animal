package com.example.animal.domain.cityprovince.service;

import com.example.animal.domain.cityprovince.entity.CityProvince;
import com.example.animal.domain.cityprovince.dto.response.CityProvinceListResponse;
import com.example.animal.domain.cityprovince.dto.response.CityProvinceResponse;
import com.example.animal.domain.cityprovince.repository.CityProvinceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CityProvinceService {
    private final CityProvinceRepository cityProvinceRepository;

    //시도 정보 저장
    public List<CityProvince> saveAll(CityProvinceListResponse response) {
        return cityProvinceRepository.saveAll(response.getCitiesProvinces().stream()
                .map(CityProvinceResponse::toEntity)
                .toList());
    }
}
