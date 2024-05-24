package com.example.animal.domain.district.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Getter
public class DistrictListResponse {
    @JsonProperty("item")
    private List<DistrictResponse> districts;

    @JsonCreator
    public DistrictListResponse(@JsonProperty("response") JsonNode node) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode itemNode = node.findValue("item");
        //결과가 없다면 빈 배열을 추가
        this.districts = Arrays.stream(
                Optional.ofNullable(objectMapper.treeToValue(itemNode, DistrictResponse[].class)
                ).orElse(new DistrictResponse[0])).toList();
    }
}
