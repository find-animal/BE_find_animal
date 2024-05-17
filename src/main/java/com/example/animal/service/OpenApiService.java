package com.example.animal.service;

import com.example.animal.cllient.OpenApiClient;
import com.example.animal.dto.response.breed.BreedsListResponse;
import com.example.animal.dto.response.cityprovince.CityProvinceListResponse;
import com.example.animal.dto.response.district.DistrictListResponse;
import com.example.animal.dto.response.shelter.ShelterListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OpenApiService {

    private final OpenApiClient client;

    public DistrictListResponse loadDistrict(String uprCd) {
        DistrictListResponse districts = client.loadDistrict(uprCd);

        return districts;
    }

    public CityProvinceListResponse loadCityProvince() {
        CityProvinceListResponse citiesProvinces = client.loadCityProvince(17, 1);

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
}
