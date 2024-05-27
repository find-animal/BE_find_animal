package com.example.animal.domain.animal.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AnimalListOpenApiResponse {

    @JsonProperty("item")
    private List<AnimalOpenApiResponse> animals = new ArrayList<>();

    @JsonProperty("pageNo")
    private Integer pageNo;

    @JsonProperty("totalCount")
    private Integer totalCount;

    @JsonCreator
    public AnimalListOpenApiResponse(@JsonProperty("response") JsonNode node) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode itemNode = node.findValue("item");
        this.animals = Arrays.stream(
            Optional.ofNullable(objectMapper.treeToValue(itemNode, AnimalOpenApiResponse[].class)
            ).orElse(new AnimalOpenApiResponse[0])).toList();

        JsonNode pageNoNode = node.findValue("pageNo");
        this.pageNo = objectMapper.treeToValue(pageNoNode, Integer.class);

        JsonNode totalCountNode = node.findValue("totalCount");
        this.totalCount = objectMapper.treeToValue(totalCountNode, Integer.class);
    }
}
