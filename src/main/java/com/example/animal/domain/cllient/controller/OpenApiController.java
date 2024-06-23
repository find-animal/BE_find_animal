package com.example.animal.domain.cllient.controller;

import com.example.animal.domain.animal.dto.response.AnimalListOpenApiResponse;
import com.example.animal.domain.animal.service.AnimalService;
import com.example.animal.domain.cityprovince.dto.response.CityProvinceListOpenApiResponse;
import com.example.animal.domain.cityprovince.service.CityProvinceService;
import com.example.animal.domain.cllient.service.OpenApiService;
import com.example.animal.domain.district.dto.response.DistrictListResponse;
import com.example.animal.domain.district.service.DistrictService;
import com.example.animal.domain.shelter.dto.response.ShelterListOpenApiResponse;
import com.example.animal.domain.shelter.entity.Shelter;
import com.example.animal.domain.shelter.service.ShelterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "공공데이터 api test", description = "공공데이터를 활용한 데이터 저장용 API")
@RequiredArgsConstructor
@RestController
public class OpenApiController {

  private final ShelterService shelterService;
  private final CityProvinceService cityProvinceService;
  private final DistrictService districtService;
  private final OpenApiService openApiService;
  private final AnimalService animalService;

  private static final Long SHELTER_FIRST = 1L;
  private static final Long SHELTER_LAST = 253L;

  @Operation(summary = "보호소 동물 정보 조회 및 저장", description = "보호소 동물 정보를 조회하고 저장합니다.")
  @GetMapping("/open-api/animal")
  public ResponseEntity<List<AnimalListOpenApiResponse>> loadSaveAnimal() {
    List<AnimalListOpenApiResponse> allAnimals = new ArrayList<>();
    for (Long i = SHELTER_FIRST; i <= SHELTER_LAST; i++) {
      List<Shelter> shelters = shelterService.findByDistrictId(i);
      for (Shelter shelter : shelters) {
        AnimalListOpenApiResponse animals = openApiService.loadAnimals(shelter.getCareRegNo());
        animalService.saveAll(animals, shelter.getCareRegNo());
        allAnimals.add(animals);
      }
    }
    return ResponseEntity.ok()
        .body(allAnimals);
  }

  @Operation(summary = "시군구 정보 조회 및 저장", description = "시군구 정보를 조회하고 저장합니다.")
  @Parameter(name = "upr_cd", description = "시군구 상위코드(시도코드) 미입력시 데이터 x")
  @GetMapping("/open-api/district")
  public ResponseEntity<DistrictListResponse> loadSaveDistrict(
      @RequestParam(value = "upr_cd") String uprCd) {
    DistrictListResponse districts = openApiService.loadDistrict(uprCd);
    districtService.saveAll(districts, uprCd);

    return ResponseEntity.ok()
        .body(districts);
  }

  @Operation(summary = "시도 정보 조회 및 저장", description = "시도 정보를 조회하고 저장합니다.")
  @GetMapping("/open-api/city-province")
  public ResponseEntity<CityProvinceListOpenApiResponse> loadSaveCityProvince() {
    CityProvinceListOpenApiResponse citiesProvinces = openApiService.loadCityProvince();

    cityProvinceService.saveAll(citiesProvinces);

    return ResponseEntity.ok()
        .body(citiesProvinces);
  }


  @Operation(summary = "보호소 조회 및 저장", description = "파라미터로 받은 시도, 시군구코드를 통해 보호소를 조회하고 저장합니다.")
  @Parameter(name = "uprCd", description = "시도코드 미입력시 데이터 x")
  @Parameter(name = "orgCd", description = "시군구코드 미입력시 데이터 x")
  @GetMapping("/open-api/shelter")
  public ResponseEntity<ShelterListOpenApiResponse> loadSaveShelter(
      @RequestParam(value = "uprCd") String uprCd, @RequestParam(value = "orgCd") String orgCd) {
    ShelterListOpenApiResponse shelters = openApiService.loadShelter(uprCd, orgCd);
    shelterService.saveAll(shelters, orgCd);

    return ResponseEntity.ok()
        .body(shelters);

  }
}
