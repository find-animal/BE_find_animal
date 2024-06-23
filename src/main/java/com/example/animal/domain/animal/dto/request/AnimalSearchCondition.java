package com.example.animal.domain.animal.dto.request;

import com.example.animal.domain.enums.SexType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record AnimalSearchCondition(
    @Schema(description = "태어난 연도 (시작)", example = "2022")
    String startYear,
    @Schema(description = "태어난 연도 (끝 - 1)", example = "2023")
    String endYear,
    @Schema(description = "성별 코드", example = "M")
    SexType sexCd,
    @Schema(description = "현재 입양 가능 여부", example = "true", defaultValue = "false")
    Boolean canAdopt,

    @Schema(description = "시도 코드 ID 여러개 가능", example = "2, 3")
    List<Long> cityProvinceIds
) {

  public AnimalSearchCondition {
    if (canAdopt == null) {
      canAdopt = false;
    }
  }
}
