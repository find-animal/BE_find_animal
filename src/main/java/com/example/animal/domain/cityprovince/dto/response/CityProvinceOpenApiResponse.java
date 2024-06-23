package com.example.animal.domain.cityprovince.dto.response;

import com.example.animal.domain.cityprovince.entity.CityProvince;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CityProvinceOpenApiResponse {
    //시도코드
    @JsonProperty("orgCd")
    private String orgCd;
    //시도명
    @JsonProperty("orgdownNm")
    private String orgdownNm;

    public CityProvince toEntity() {
        return CityProvince.builder()
                .orgCd(this.orgCd)
                .orgdownNm(this.orgdownNm)
                .build();
    }
}
