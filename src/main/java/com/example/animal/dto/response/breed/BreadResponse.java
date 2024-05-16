package com.example.animal.dto.response.breed;

import com.example.animal.domain.Breed;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BreadResponse {
    //품종코드
    @JsonProperty("kindCd")
    private String kindCd;
    //품종명
    @JsonProperty("knm")
    private String knm;

    public Breed toEntity() {
        return Breed.builder()
                .kindCd(this.kindCd)
                .knm(this.knm)
                .build();
    }

}
