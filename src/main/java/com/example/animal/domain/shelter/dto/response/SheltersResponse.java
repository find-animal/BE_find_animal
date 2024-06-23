package com.example.animal.domain.shelter.dto.response;

import com.example.animal.domain.shelter.entity.Shelter;
import lombok.Builder;

@Builder
public record SheltersResponse(
    Long id,
    String careNm,
    String careRegNo,
    String careAddr,
    String careTel,
    String orgNm
) {

  public static SheltersResponse fromEntity(Shelter shelter) {
    return SheltersResponse.builder()
        .id(shelter.getId())
        .careNm(shelter.getCareNm())
        .careRegNo(shelter.getCareRegNo())
        .careAddr(shelter.getCareAddr())
        .careTel(shelter.getCareTel())
        .orgNm(shelter.getOrgNm())
        .build();
  }
}