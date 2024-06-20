package com.example.animal.domain.shelter.controller;

import com.example.animal.domain.shelter.dto.ShelterSearchCondition;
import com.example.animal.domain.shelter.dto.response.ShelterPageResponse;
import com.example.animal.domain.shelter.service.ShelterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "보호소 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("${server.api.prefix}/shelter")
public class ShelterController {

  private final ShelterService shelterService;

  @Operation(summary = "보호소 리스트 조회")
  @GetMapping("/all")
  public ResponseEntity<ShelterPageResponse> findAll(
      @RequestParam("pageNo") int pageNo,
      @RequestParam(value = "cityProvinceId", required = false) List<Long> cityProvinceId
  ) {
    ShelterSearchCondition shelterSearchCondition = new ShelterSearchCondition(pageNo,
        cityProvinceId);
    ShelterPageResponse shelters = shelterService.getFilteredShelterList(shelterSearchCondition);
    
    return ResponseEntity.ok()
        .body(shelters);
  }
}
