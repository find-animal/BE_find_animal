package com.example.animal.domain.cityprovince.controller;

import com.example.animal.domain.cityprovince.dto.response.CityProvinceResponse;
import com.example.animal.domain.cityprovince.service.CityProvinceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "시도 API", description = "시도 정보 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("${server.api.prefix}/cityProvince")
public class CityProvinceController {

  private final CityProvinceService cityProvinceService;

  @Operation(summary = "시도 리스트 조회", description = "시도 정보가 조회됩니다.")
  @GetMapping("")
  public ResponseEntity<List<CityProvinceResponse>> findCityProvince() {
    List<CityProvinceResponse> cityProvinces = cityProvinceService.findAll();
    return ResponseEntity.ok()
        .body(cityProvinces);
  }

}
