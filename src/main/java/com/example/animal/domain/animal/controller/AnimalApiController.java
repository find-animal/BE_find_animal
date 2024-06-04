package com.example.animal.domain.animal.controller;

import com.example.animal.domain.animal.dto.request.FilterAnimalRequest;
import com.example.animal.domain.animal.dto.response.AnimalPageResponse;
import com.example.animal.domain.animal.dto.response.AnimalResponse;
import com.example.animal.domain.animal.service.AnimalService;
import com.example.animal.exception.dto.response.CustomErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유기동물 API", description = "유기 동물 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("${server.api.prefix}/animals")
public class AnimalApiController {

  private final AnimalService animalService;

  @Operation(summary = "유기동물 상세 정보 조회", description = "유기 동물의 정보가 조회됩니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "성공",
      content = {@Content(schema = @Schema(implementation = AnimalResponse.class))}),
      @ApiResponse(responseCode = "500",description = "db에 해당 id값을 가진 데이터가 없음",
      content = {@Content(schema = @Schema(implementation = CustomErrorResponse.class))})
  })
  @GetMapping("/{id}")
  public ResponseEntity<AnimalResponse> findAnimal(@Valid @PathVariable(name = "id") Long id) {
    AnimalResponse animal = animalService.getAnimalDetail(id);

    return ResponseEntity.ok()
        .body(animal);
  }

  @Operation(summary = "유기동물 리스트를 조회", description = "필터링된 유기동물을 조회합니다.")
  @GetMapping("")
  public ResponseEntity<AnimalPageResponse> findAnimals(
      @Valid @ParameterObject @ModelAttribute FilterAnimalRequest filterAnimalRequest,
      @ParameterObject @PageableDefault(sort = "noticeSdt", direction = Direction.DESC) Pageable pageable
  ) {
    AnimalPageResponse animals = animalService.getFilteredAnimalList(filterAnimalRequest,
        pageable);

    return ResponseEntity.ok()
        .body(animals);
  }
}
