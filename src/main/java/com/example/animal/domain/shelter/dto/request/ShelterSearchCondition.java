package com.example.animal.domain.shelter.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record ShelterSearchCondition(
    int pageNo,
    @Schema(description = "시도 코드 ID 여러개 가능", example = "2, 3")
    List<Long> cityProvinceIds
) {
}
