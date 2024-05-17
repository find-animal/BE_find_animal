package com.example.animal.cllient;

import com.example.animal.dto.response.breed.BreedsListResponse;
import com.example.animal.dto.response.cityprovince.CityProvinceListResponse;
import com.example.animal.dto.response.district.DistrictListResponse;
import com.example.animal.dto.response.shelter.ShelterListResponse;
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

    @GetMapping("/kind")
    BreedsListResponse loadBreeds(
            @RequestParam(value = "up_kind_cd") String upKindCd
    );

}
