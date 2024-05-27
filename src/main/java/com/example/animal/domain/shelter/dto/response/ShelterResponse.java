package com.example.animal.domain.shelter.dto.response;

import com.example.animal.domain.shelter.entity.Shelter;
import lombok.Getter;

@Getter
public class ShelterResponse {

  private String careNm;
  private String careAddr;
  private String careTel;
  private String chargeNm;
  private String officeTel;

  public ShelterResponse(Shelter shelter) {
    this.careNm = shelter.getCareNm();
    this.careAddr = shelter.getCareAddr();
    this.careTel = shelter.getCareTel();
    this.chargeNm = shelter.getChargeNm();
    this.officeTel = shelter.getOfficeTel();
  }
}
