package com.example.animal.domain.cityprovince.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Getter
public class CityProvinceListOpenApiResponse {
    @JsonProperty("item")
    private List<CityProvinceOpenApiResponse> citiesProvinces;
    //전체 결과수
    @JsonProperty("totalCount")
    private Integer totalCount;

    @JsonCreator
    public CityProvinceListOpenApiResponse(@JsonProperty("response") JsonNode node) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode itemNode = node.findValue("item");
        this.citiesProvinces = Arrays.stream(objectMapper.treeToValue(itemNode, CityProvinceOpenApiResponse[].class)).toList();

        JsonNode totalCountNode = node.findValue("totalCount");
        this.totalCount = objectMapper.treeToValue(totalCountNode,Integer.class);
    }

}
