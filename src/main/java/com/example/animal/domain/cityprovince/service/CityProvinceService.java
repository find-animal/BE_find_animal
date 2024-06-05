package com.example.animal.domain.cityprovince.service;

import com.example.animal.domain.cityprovince.dto.response.CityProvinceListOpenApiResponse;
import com.example.animal.domain.cityprovince.dto.response.CityProvinceOpenApiResponse;
import com.example.animal.domain.cityprovince.dto.response.CityProvinceResponse;
import com.example.animal.domain.cityprovince.entity.CityProvince;
import com.example.animal.domain.cityprovince.repository.CityProvinceRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CityProvinceService {

  private final CityProvinceRepository cityProvinceRepository;

  //시도 정보 저장
  public List<CityProvince> saveAll(CityProvinceListOpenApiResponse response) {
    return cityProvinceRepository.saveAll(response.getCitiesProvinces().stream()
        .map(CityProvinceOpenApiResponse::toEntity)
        .toList());
  }

  //시도 정보 조회
  public List<CityProvinceResponse> findAll() {
    return cityProvinceRepository.findAll()
        .stream().map(CityProvinceResponse::toEntity)
        .toList();
  }
}
