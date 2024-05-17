package com.example.animal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenApiService {
    private final ObjectMapper objectMapper;
    public <T> T parsingJsonObject(String json, Class<T> responseType) {
        T result = null;

        try {
            result = objectMapper.readValue(json, responseType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
