package com.example.animal.domain.user.dto.response;

import java.util.List;
import lombok.Builder;

@Builder
public record FavoriteShelterResponse(
    List<Long> favoriteShelters
) {

}
