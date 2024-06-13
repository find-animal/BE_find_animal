package com.example.animal.domain.user.controller;

import com.example.animal.domain.user.dto.request.AddFavoriteAnimalRequest;
import com.example.animal.domain.user.dto.response.FavoriteAnimalResponse;
import com.example.animal.domain.user.service.UserFavoriteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유저 관심 동물 api")
@RestController
@RequiredArgsConstructor
@RequestMapping("${server.api.prefix}/user")
public class UserController {

  private final UserFavoriteService userFavoriteService;

  @Operation(summary = "관심 유기동물 추가", description = "관심 유기동물이 user 정보에 저장이 됩니다.")
  @PostMapping("/saveAnimal")
  public ResponseEntity<FavoriteAnimalResponse> addFavoriteAnimal(
      @Valid @RequestBody AddFavoriteAnimalRequest addFavoriteAnimalRequest) {
    FavoriteAnimalResponse response = userFavoriteService.saveFavoriteAnimal(
        addFavoriteAnimalRequest);

    return ResponseEntity.ok()
        .body(response);
  }
}
