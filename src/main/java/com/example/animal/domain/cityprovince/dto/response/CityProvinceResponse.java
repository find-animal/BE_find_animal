package com.example.animal.domain.cityprovince.dto.response;

import com.example.animal.domain.cityprovince.entity.CityProvince;
import lombok.Builder;

@Builder
public record CityProvinceResponse(
    Long id,

    String orgdownNm
) {

  public static CityProvinceResponse fromEntity(CityProvince cityProvince) {
    return CityProvinceResponse.builder()
        .id(cityProvince.getId())
        .orgdownNm(cityProvince.getOrgdownNm())
        .build();
  }
}
