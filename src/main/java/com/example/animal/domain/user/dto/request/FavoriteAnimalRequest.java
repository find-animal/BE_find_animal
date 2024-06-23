package com.example.animal.domain.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record FavoriteAnimalRequest(
    @Schema(description = "userId O nickname X")
    @NotNull(message = "유저의 Id값을 넣어주세요.")
    Long userId,
    @Schema(description = "유기동물 Id")
    @NotNull(message = "유기동물의 Id값을 넣어주세요.")
    Long animalId
) {

}
