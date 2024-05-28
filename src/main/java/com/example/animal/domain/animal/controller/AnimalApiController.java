package com.example.animal.domain.animal.controller;

import com.example.animal.domain.animal.dto.response.AnimalListResponse;
import com.example.animal.domain.animal.dto.response.AnimalResponse;
import com.example.animal.domain.animal.service.AnimalService;
import com.example.animal.domain.enums.SexType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유기동물 API", description = "유기 동물 관련 API")
@RestController
@RequiredArgsConstructor
public class AnimalApiController {

  private final AnimalService animalService;

  @Operation(summary = "유기동물 상세 정보 조회", description = "유기 동물의 정보가 조회됩니다.")
  @GetMapping("/api/animals/{id}")
  public ResponseEntity<AnimalResponse> findAnimal(@PathVariable(name = "id") Long id) {
    AnimalResponse animal = animalService.getAnimalDetail(id);

    return ResponseEntity.ok()
        .body(animal);
  }

  @Operation(summary = "유기동물 리스트를 조회", description = "필터링된 유기동물을 조회합니다."
      )
  @GetMapping("/api/animals")
  public ResponseEntity<List<AnimalListResponse>> findAnimals(
      @Parameter(
          name = "startYear",
          description = "출생연도 시작 연도",
          example = "2023",
          required = false)
      @RequestParam String startYear,
      @Parameter(
          name = "endYear",
          description = "출생연도 마지막 연도 |2023년생을 조회 -> startYear : 2023, endYear: 2024",
          example = "2024",
          required = false)
      @RequestParam String endYear,
      @Parameter(
          name = "sexCd",
          description = "성별",
          example = "M",
          required = false)
      @RequestParam SexType sexCd,
      @Parameter(
          name = "startDate",
          description = "보호소기간 조회 시작 기간",
          example = "2024-05-23",
          required = false)
      @RequestParam
      @DateTimeFormat(iso = ISO.DATE) LocalDate startDate,
      @Parameter(
          name = "endDate",
          description = "보호소기간 조회 마지막 기간",
          example = "2024-05-24",
          required = false)
      @RequestParam
      @DateTimeFormat(iso = ISO.DATE) LocalDate endDate,
      @Parameter(
          name = "noticeSdt",
          description = "조회할 페이지 번호와 페이지 내 아이템 개수",
          example = "{\"page\": 0,\n"
              + "  \"size\": 10}",
          required = true)
      @PageableDefault(sort = "noticeSdt", direction = Direction.DESC) Pageable pageable

  ) {
    List<AnimalListResponse> animals = animalService.getFilteredAnimalList(startYear, endYear,
        sexCd, startDate, endDate, pageable);

    return ResponseEntity.ok()
        .body(animals);
  }
}
