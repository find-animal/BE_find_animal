package com.example.animal.service;

import com.example.animal.domain.CityProvince;
import com.example.animal.dto.response.cityprovince.CityProvinceListResponse;
import com.example.animal.dto.response.cityprovince.CityProvinceResponse;
import com.example.animal.repository.CityProvinceRepository;
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
