package com.example.animal.cllient;

import com.example.animal.dto.response.district.DistrictListResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "openapi", url = "http://apis.data.go.kr/1543061/abandonmentPublicSrvc")
public interface OpenApiClient {
    @GetMapping("/sigungu")
    DistrictListResponse loadDistrict(
            @RequestParam(value = "upr_cd") String uprCd,
            @RequestParam(value = "serviceKey") String serviceKey,
            @RequestParam(value = "_type") String type
    );
}
