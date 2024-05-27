package com.example.animal.domain.animal.controller;

import com.example.animal.domain.animal.dto.response.AnimalResponse;
import com.example.animal.domain.animal.dto.response.AnimalListResponse;
import com.example.animal.domain.animal.service.AnimalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유기동물 API", description = "유기 동물 관련 API")
@RestController
@RequiredArgsConstructor
public class AnimalApiController {

  private final AnimalService animalService;

  @Operation(summary = "유기동물 리스트를 조회", description = "파라미터로 받은 page와 size 만큼 동물이 조회가 됩니다.\ndefault value: page = 0, size = 10")
  @GetMapping("/api/animals")
  public ResponseEntity<List<AnimalListResponse>> findAllAnimals(
      @PageableDefault(sort = "noticeSdt", direction = Direction.DESC) Pageable pageable) {
    List<AnimalListResponse> animals = animalService.getAnimalList(pageable);

    return ResponseEntity.ok()
        .body(animals);
  }

  @Operation(summary = "유기동물 상세 정보 조회", description = "파라미터로 받은 id값에 해당하는 동물의 정보가 조회됩니다.")
  @GetMapping("/api/animals/{id}")
  public ResponseEntity<AnimalResponse> findAnimal(@PathVariable(name = "id") Long id) {
    AnimalResponse animal = animalService.getAnimalDetail(id);

    return ResponseEntity.ok()
        .body(animal);
  }
}
