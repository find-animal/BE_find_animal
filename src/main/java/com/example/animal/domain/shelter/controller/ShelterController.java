package com.example.animal.domain.shelter.controller;

import com.example.animal.domain.shelter.dto.request.ShelterSearchCondition;
import com.example.animal.domain.shelter.dto.response.ShelterPageResponse;
import com.example.animal.domain.shelter.dto.response.ShelterResponse;
import com.example.animal.domain.shelter.dto.response.SheltersResponse;
import com.example.animal.domain.shelter.service.ShelterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "보호소 API", description = "보호소 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("${server.api.prefix}/shelter")
public class ShelterController {

  private final ShelterService shelterService;

  @Operation(summary = "보호소 상세 정보 조회", description = "보호소의 정보가 조회됩니다.")
  @GetMapping("/{id}")
  public ResponseEntity<ShelterResponse> findShelter(
      @PathVariable(name = "id") Long id
  ) {
    ShelterResponse shelter = shelterService.getShelterDetail(id);

    return ResponseEntity.ok()
        .body(shelter);
  }

  @Operation(summary = "관심 보호소 리스트 조회", description = "관심보호소 리스트를 조회합니다.")
  @GetMapping("/favorite/{userId}")
  public ResponseEntity<List<SheltersResponse>> findFavoriteShelters(
      @PathVariable(name = "userId") Long userId
  ) {
    List<SheltersResponse> shelters = shelterService.findFavoriteShelters(userId);

    return ResponseEntity.ok()
        .body(shelters);
  }

  @Operation(summary = "보호소 리스트 조회")
  @GetMapping("/all")
  public ResponseEntity<ShelterPageResponse> findAll(
      @Valid @ParameterObject @ModelAttribute ShelterSearchCondition shelterSearchCondition
  ) {
    ShelterPageResponse shelters = shelterService.getFilteredShelterList(shelterSearchCondition);

    return ResponseEntity.ok()
        .body(shelters);
  }
}
