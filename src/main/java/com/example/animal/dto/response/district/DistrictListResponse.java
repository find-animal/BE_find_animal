package com.example.animal.dto.response.district;

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
public class DistrictListResponse {
    @JsonProperty("item")
    private List<DistrictResponse> districts;

    @JsonCreator
    public DistrictListResponse(@JsonProperty("response") JsonNode node) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode itemNode = node.findValue("item");
        this.districts = Arrays.stream(objectMapper.treeToValue(itemNode, DistrictResponse[].class)).toList();
    }
}
