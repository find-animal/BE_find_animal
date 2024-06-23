package com.example.animal.domain.user.dto.response;

import java.util.List;
import lombok.Builder;

@Builder
public record FavoriteAnimalResponse(
    List<Long> favoriteAnimals
) {

}
