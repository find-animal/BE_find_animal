package com.example.animal.service;

import com.example.animal.domain.Shelter;
import com.example.animal.dto.response.ShelterListResponse;
import com.example.animal.dto.response.ShelterResponse;
import com.example.animal.repository.ShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ShelterService {
    private final ShelterRepository shelterRepository;

    //보호소 전체 정보 저장
    public List<Shelter> saveAll(ShelterListResponse response) {
        return shelterRepository.saveAll(response.getShelters().stream()
                .map(ShelterResponse::toEntity)
                .toList());
    }
}
