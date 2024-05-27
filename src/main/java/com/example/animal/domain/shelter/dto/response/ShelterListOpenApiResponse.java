package com.example.animal.domain.shelter.dto.response;

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
public class ShelterListOpenApiResponse {
    @JsonProperty("item")
    private List<ShelterOpenApiResponse> shelters;

    @JsonCreator
    public ShelterListOpenApiResponse(@JsonProperty("response") JsonNode node) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode itemNode = node.findValue("item");
        this.shelters = Arrays.stream(objectMapper.treeToValue(itemNode, ShelterOpenApiResponse[].class)).toList();
    }
}
