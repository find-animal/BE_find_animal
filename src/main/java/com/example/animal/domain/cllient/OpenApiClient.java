package com.example.animal.domain.cllient;

import com.example.animal.domain.animal.dto.response.AnimalListResponse;
import com.example.animal.domain.cityprovince.dto.response.CityProvinceListResponse;
import com.example.animal.domain.district.dto.response.DistrictListResponse;
import com.example.animal.domain.shelter.dto.response.ShelterListResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "openapi")
public interface OpenApiClient {

  @GetMapping("/sigungu")
  DistrictListResponse loadDistrict(
      @RequestParam(value = "upr_cd") String uprCd
  );

  @GetMapping("/sido")
  CityProvinceListResponse loadCityProvince(
      @RequestParam(value = "numOfRows") int numOfRows,
      @RequestParam(value = "pageNo") int pageNo
  );

  @GetMapping("/shelter")
  ShelterListResponse loadShelter(
      @RequestParam(value = "upr_cd") String uprCd,
      @RequestParam(value = "org_cd") String orgCd
  );

  @GetMapping("/abandonmentPublic")
  AnimalListResponse loadAnimal(
      @RequestParam(value = "care_reg_no") String careRegNo,
      @RequestParam(value = "numOfRows") Integer numOfRows);

}
