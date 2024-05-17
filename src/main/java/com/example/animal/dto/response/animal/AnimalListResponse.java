package com.example.animal.dto.response.animal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AnimalListResponse {

    @JsonProperty("item")
    private List<AnimalResponse> animals = new ArrayList<>();

    @JsonProperty("numOfRows")
    private Integer numOfRows;

    @JsonProperty("pageNo")
    private Integer pageNo;

    @JsonProperty("totalCount")
    private Integer totalCount;

    @JsonCreator
    public AnimalListResponse(@JsonProperty("response") JsonNode node) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode itemNode = node.findValue("item");
        this.animals = Arrays.stream(objectMapper.treeToValue(itemNode, AnimalResponse[].class)).toList();

        JsonNode numOfRowsNode = node.findValue("numOfRowsNode");
        this.numOfRows = objectMapper.treeToValue(numOfRowsNode, Integer.class);

        JsonNode pageNoNode = node.findValue("pageNo");
        this.pageNo = objectMapper.treeToValue(pageNoNode, Integer.class);

        JsonNode totalCountNode = node.findValue("totalCount");
        this.totalCount = objectMapper.treeToValue(totalCountNode, Integer.class);
    }
}
