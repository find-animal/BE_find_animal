package com.example.animal.controller;

import com.example.animal.cllient.OpenApiClient;
import com.example.animal.config.OpenApiProperties;
import com.example.animal.dto.response.breed.BreedsListResponse;
import com.example.animal.dto.response.cityprovince.CityProvinceListResponse;
import com.example.animal.dto.response.district.DistrictListResponse;
import com.example.animal.dto.response.shelter.ShelterListResponse;
import com.example.animal.service.*;
import com.example.animal.util.HttpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Tag(name = "공공데이터 api", description = "공공데이터를 활용한 데이터 저장용 API")
@RequiredArgsConstructor
@RestController
public class OpenApiController {

    private final OpenApiProperties openApiProperties;
    private final OpenApiService openApiService;
    private final BreedService breedService;
    private final ShelterService shelterService;
    private final CityProvinceService cityProvinceService;
    private final DistrictService districtService;

    private final OpenApiClient openApiClient;


    @Operation(summary = "시군구 정보 조회 및 저장", description = "시군구 정보를 조회하고 저장합니다.")
    @Parameter(name = "uprCd", description = "시군구 상위코드(시도코드) 미입력시 데이터 x")
    @GetMapping("/open-api/district")
    public ResponseEntity<DistrictListResponse> loadSaveDistrict(@RequestParam(value = "uprCd") String uprCd) {
//        String result = null;
//
//        String urlStr = openApiProperties.getBaseUrl() + "sigungu?upr_cd="
//                + uprCd
//                + "&serviceKey="
//                + openApiProperties.getServiceKey()
//                + "&_type=json";
//
//        try {
//            result = HttpUtil.getRequest(urlStr);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        DistrictListResponse districts = openApiService.parsingJsonObject(result, DistrictListResponse.class);

        DistrictListResponse districts = openApiClient.loadDistrict(uprCd, openApiProperties.getServiceKey(), "json");
        districtService.saveAll(districts, uprCd);

        return ResponseEntity.ok()
                .body(districts);
    }

    @Operation(summary = "시도 정보 조회 및 저장", description = "시도 정보를 조회하고 저장합니다.")
    @GetMapping("/open-api/city-province")
    public ResponseEntity<CityProvinceListResponse> loadSaveCityProvince() {
        String result = null;

        String urlStr = openApiProperties.getBaseUrl() + "sido?numOfRows=17&pageNo=1&serviceKey="
                + openApiProperties.getServiceKey()
                + "&_type=json";

        try {
            result = HttpUtil.getRequest(urlStr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        CityProvinceListResponse citiesProvinces = openApiService.parsingJsonObject(result, CityProvinceListResponse.class);

        cityProvinceService.saveAll(citiesProvinces);

        return ResponseEntity.ok()
                .body(citiesProvinces);
    }


    @Operation(summary = "보호소 조회 및 저장", description = "파라미터로 받은 시도, 시군구코드를 통해 보호소를 조회하고 저장합니다.")
    @Parameter(name = "uprCd", description = "시도코드 미입력시 데이터 x")
    @Parameter(name = "orgCd", description = "시군구코드 미입력시 데이터 x")
    @GetMapping("/open-api/shelter")
    public ResponseEntity<ShelterListResponse> loadSaveShelter(@RequestParam(value = "uprCd") String uprCd, @RequestParam(value = "orgCd") String orgCd) {
        String result = null;

        String urlStr = openApiProperties.getBaseUrl() + "shelter?upr_cd="
                + uprCd
                + "&org_cd="
                + orgCd
                + "&serviceKey="
                + openApiProperties.getServiceKey()
                + "&_type=json";

        try {
            result = HttpUtil.getRequest(urlStr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ShelterListResponse shelters = openApiService.parsingJsonObject(result, ShelterListResponse.class);

        shelterService.saveAll(shelters, uprCd);

        return ResponseEntity.ok()
                .body(shelters);

    }

    @Operation(summary = "품종 조회 및 저장", description = "파라미터로 받은 품종을 조회하고 저장합니다.")
    @Parameter(name = "upKindCd", description = "개: 417000, 고양이: 422400, 기타: 429900")
    @GetMapping("/open-api/breed/{upKindCd}")
    public ResponseEntity<BreedsListResponse> loadSaveBreads(@PathVariable(name = "upKindCd") String upKindCd) {
        String result = null;

        String urlStr = openApiProperties.getBaseUrl() + "kind?up_kind_cd="
                + upKindCd
                + "&serviceKey="
                + openApiProperties.getServiceKey()
                + "&_type=json";

        try {
            result = HttpUtil.getRequest(urlStr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BreedsListResponse animals = openApiService.parsingJsonObject(result, BreedsListResponse.class);

        breedService.saveAll(animals);

        return ResponseEntity.ok()
                .body(animals);
    }
}
