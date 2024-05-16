package com.example.animal.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnimalResponse {
    //품종코드
    @JsonProperty("kindCd")
    private String kindCd;
    //품종명
    @JsonProperty("knm")
    private String knm;

}
