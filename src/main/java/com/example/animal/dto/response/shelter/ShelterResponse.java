package com.example.animal.dto.response.shelter;

import com.example.animal.domain.Shelter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShelterResponse {
    //보호소 번호
    @JsonProperty("careRegNo")
    private String careRegNo;
    //보호소명
    @JsonProperty("careNm")
    private String careNm;

    public Shelter toEntity() {
        return Shelter.builder()
                .careRegNo(this.careRegNo)
                .careNm(this.careNm)
                .build();
    }
}
