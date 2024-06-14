package com.example.animal.domain.shelter.dto.response;

public record SheltersResponse (
        Long id,
        String careNm,
        String careRegNo,
        String careAddr,
        String careTel
){
}