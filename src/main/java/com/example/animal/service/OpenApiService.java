package com.example.animal.service;

import com.example.animal.dto.response.BreedsListResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class OpenApiService {

    public BreedsListResponse parsingJsonObject(String json) {
        BreedsListResponse animals = null;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            animals = objectMapper.readValue(json, BreedsListResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return animals;
    }
}
