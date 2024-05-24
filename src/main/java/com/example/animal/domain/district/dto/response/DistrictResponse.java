package com.example.animal.domain.district.dto.response;

import com.example.animal.domain.cityprovince.entity.CityProvince;
import com.example.animal.domain.district.entity.District;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DistrictResponse {
    //시군구상위코드
    @JsonProperty("uprCd")
    private String uprCd;
    //시군구코드
    @JsonProperty("orgCd")
    private String orgCd;
    //시군구명
    @JsonProperty("orgdownNm")
    private String orgdownNm;

    public District toEntity(CityProvince cityProvince) {
        return District.builder()
                .orgdownNm(this.orgdownNm)
                .orgCd(this.orgCd)
                .cityProvince(cityProvince)
                .build();
    }
}
