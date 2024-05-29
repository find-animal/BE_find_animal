package com.example.animal.domain.shelter.dto.response;

import com.example.animal.domain.shelter.entity.Shelter;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShelterResponse {

  private String careNm;
  private String careAddr;
  private String careTel;
  private String chargeNm;
  private String officeTel;

  public static ShelterResponse fromEntity(Shelter shelter) {
    return ShelterResponse.builder()
        .careNm(shelter.getCareNm())
        .careAddr(shelter.getCareAddr())
        .careTel(shelter.getCareTel())
        .chargeNm(shelter.getChargeNm())
        .officeTel(shelter.getOfficeTel())
        .build();
  }
}
