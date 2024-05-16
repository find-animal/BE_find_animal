package com.example.animal.service;

import com.example.animal.dto.response.AnimalsListResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class OpenApiService {

    public AnimalsListResponse parsingJsonObject(String json) {
        AnimalsListResponse animals = null;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            animals = objectMapper.readValue(json, AnimalsListResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return animals;
    }
}
