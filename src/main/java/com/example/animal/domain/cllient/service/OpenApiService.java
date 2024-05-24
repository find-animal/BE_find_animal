package com.example.animal.domain.cllient.service;

import com.example.animal.domain.cllient.OpenApiClient;
import com.example.animal.domain.animal.dto.response.AnimalListResponse;
import com.example.animal.dto.response.breed.BreedsListResponse;
import com.example.animal.domain.cityprovince.dto.response.CityProvinceListResponse;
import com.example.animal.domain.district.dto.response.DistrictListResponse;
import com.example.animal.domain.shelter.dto.response.ShelterListResponse;
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

    public CityProvinceListResponse loadCityProvince() {
        CityProvinceListResponse citiesProvinces = client.loadCityProvince(CITY_ROW_NUM, CITY_PAGE_NUM);

        return citiesProvinces;
    }

    public ShelterListResponse loadShelter(String uprCd, String orgCd) {
        ShelterListResponse shelters = client.loadShelter(uprCd, orgCd);

        return shelters;
    }

    public BreedsListResponse loadBreed(String upKindCd) {
        BreedsListResponse breeds = client.loadBreeds(upKindCd);

        return breeds;
    }

    public AnimalListResponse loadAnimals(String careRegNo) {
        AnimalListResponse animals = client.loadAnimal(careRegNo, ANIMAL_ROW_NUM);
        return animals;
    }
}
