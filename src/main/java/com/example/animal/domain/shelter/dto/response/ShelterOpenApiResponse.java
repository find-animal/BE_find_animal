package com.example.animal.domain.shelter.dto.response;

import com.example.animal.domain.district.entity.District;
import com.example.animal.domain.shelter.entity.Shelter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShelterOpenApiResponse {
    //보호소 번호
    @JsonProperty("careRegNo")
    private String careRegNo;
    //보호소명
    @JsonProperty("careNm")
    private String careNm;

    public Shelter toEntity(District district) {
        return Shelter.builder()
                .careRegNo(this.careRegNo)
                .careNm(this.careNm)
                .district(district)
                .build();
    }
}
