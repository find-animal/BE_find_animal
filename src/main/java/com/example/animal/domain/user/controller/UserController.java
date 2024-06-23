package com.example.animal.domain.user.controller;

import com.example.animal.domain.user.dto.request.FavoriteAnimalRequest;
import com.example.animal.domain.user.dto.request.FavoriteShelterRequest;
import com.example.animal.domain.user.dto.response.FavoriteResponse;
import com.example.animal.domain.user.service.UserFavoriteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유저 관심 관련 api")
@RestController
@RequiredArgsConstructor
@RequestMapping("${server.api.prefix}/user")
public class UserController {

  private final UserFavoriteService userFavoriteService;

  @Operation(summary = "관심 보호소 추가", description = "관심 보호소가 user 정보에 저장이 됩니다.")
  @PostMapping("/shelter")
  public ResponseEntity<FavoriteResponse> addFavoriteShelter(
      @Valid @RequestBody FavoriteShelterRequest favoriteShelterRequest) {
    FavoriteResponse response = userFavoriteService.saveFavoriteShelter(
        favoriteShelterRequest);

    return ResponseEntity.ok()
        .body(response);
  }

  @Operation(summary = "관심 보호소 삭제")
  @DeleteMapping("/shelter")
  public ResponseEntity<FavoriteResponse> deleteFavoriteShelter(
      @Valid @RequestBody FavoriteShelterRequest favoriteShelterRequest
  ) {
    FavoriteResponse response = userFavoriteService.deleteFavoriteShelter(
        favoriteShelterRequest
    );

    return ResponseEntity.ok()
        .body(response);
  }

  @Operation(summary = "관심 유기동물 추가", description = "관심 유기동물이 user 정보에 저장이 됩니다.")
  @PostMapping("/animal")
  public ResponseEntity<FavoriteResponse> addFavoriteAnimal(
      @Valid @RequestBody FavoriteAnimalRequest favoriteAnimalRequest) {
    FavoriteResponse response = userFavoriteService.saveFavoriteAnimal(
        favoriteAnimalRequest);

    return ResponseEntity.ok()
        .body(response);
  }

  @Operation(summary = "관심 유기동물 삭제")
  @DeleteMapping("/animal")
  public ResponseEntity<FavoriteResponse> deleteFavoriteAnimal(
      @Valid @RequestBody FavoriteAnimalRequest favoriteAnimalRequest
  ) {
    FavoriteResponse response = userFavoriteService.deleteFavoriteAnimal(
        favoriteAnimalRequest
    );

    return ResponseEntity.ok()
        .body(response);
  }
}
