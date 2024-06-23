package com.example.animal.domain.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record FavoriteShelterRequest(
    @Schema(description = "userId O nickname X")
    @NotNull(message = "유저의 Id값을 넣어주세요.")
    Long userId,
    @Schema(description = "보호소 Id")
    @NotNull(message = "보호소의 Id값을 넣어주세요.")
    Long shelterId
) {

}
