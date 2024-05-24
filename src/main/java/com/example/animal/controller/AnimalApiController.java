package com.example.animal.controller;

import com.example.animal.dto.response.animal.AnimalListViewResponse;
import com.example.animal.service.AnimalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유기동물 API", description = "유기 동물 관련 API")
@RestController
@RequiredArgsConstructor
public class AnimalApiController {

  private final AnimalService animalService;

  @Operation(summary = "유기동물 리스트를 조회", description = "파라미터로 받은 page와 size 만큼 동물이 조회가 됩니다.")
  @GetMapping("/api/animals")
  public ResponseEntity<List<AnimalListViewResponse>> findAllAnimals(
      @PageableDefault(page = 0, sort = "noticeSdt", direction = Direction.DESC) Pageable pageable) {
    List<AnimalListViewResponse> animals = animalService.getAnimalList(pageable)
        .stream()
        .map(AnimalListViewResponse::new)
        .toList();

    return ResponseEntity.ok()
        .body(animals);
  }
}
