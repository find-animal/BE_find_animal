package com.example.animal.service;

import com.example.animal.domain.CityProvince;
import com.example.animal.domain.District;
import com.example.animal.dto.response.district.DistrictListResponse;
import com.example.animal.repository.CityProvinceRepository;
import com.example.animal.repository.DistrictRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DistrictService {
    private final DistrictRepository districtRepository;
    private final CityProvinceRepository cityProvinceRepository;

    //시군구 정보 저장
    public List<District> saveAll(DistrictListResponse response, String uprCd) { //여기서 uprCd는 시도코드이다.
        CityProvince cityProvince = cityProvinceRepository.findByOrgCd(uprCd)
                .orElseThrow(() -> new IllegalArgumentException("Not Found cityProvince"));

        return districtRepository.saveAll(response.getDistricts().stream()
                .map((district) -> district.toEntity(cityProvince))
                .toList());

    }
}
