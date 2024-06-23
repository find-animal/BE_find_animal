package com.example.animal.domain.cllient.service;

import com.example.animal.domain.animal.dto.response.AnimalListOpenApiResponse;
import com.example.animal.domain.cityprovince.dto.response.CityProvinceListOpenApiResponse;
import com.example.animal.domain.cllient.OpenApiClient;
import com.example.animal.domain.district.dto.response.DistrictListResponse;
import com.example.animal.domain.shelter.dto.response.ShelterListOpenApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OpenApiService {

  private final OpenApiClient client;

  private static final int CITY_ROW_NUM = 17;
  private static final int CITY_PAGE_NUM = 1;
  private static final int ANIMAL_ROW_NUM = 1000;

  public DistrictListResponse loadDistrict(String uprCd) {
    DistrictListResponse districts = client.loadDistrict(uprCd);

    return districts;
  }

  public CityProvinceListOpenApiResponse loadCityProvince() {
    CityProvinceListOpenApiResponse citiesProvinces = client.loadCityProvince(CITY_ROW_NUM, CITY_PAGE_NUM);

    return citiesProvinces;
  }

  public ShelterListOpenApiResponse loadShelter(String uprCd, String orgCd) {
    ShelterListOpenApiResponse shelters = client.loadShelter(uprCd, orgCd);

    return shelters;
  }

  public AnimalListOpenApiResponse loadAnimals(String careRegNo) {
    AnimalListOpenApiResponse animals = client.loadAnimal(careRegNo, ANIMAL_ROW_NUM);
    return animals;
  }
}
