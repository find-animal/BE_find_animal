package com.example.animal.dto.response.breed;

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
public class BreedsListResponse {
    @JsonProperty("item")
    private List<BreadResponse> breeds;

    @JsonCreator
    public BreedsListResponse(@JsonProperty("response") JsonNode node) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode itemNode = node.findValue("item");
        this.breeds = Arrays.stream(objectMapper.treeToValue(itemNode, BreadResponse[].class)).toList();
    }

}
