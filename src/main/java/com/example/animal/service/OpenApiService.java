package com.example.animal.service;

import com.example.animal.dto.response.BreedsListResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class OpenApiService {

    public <T> T parsingJsonObject(String json, Class<T> responseType) {
        T result = null;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            result = objectMapper.readValue(json, responseType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
