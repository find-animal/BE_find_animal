package com.example.animal.domain.animal.dto.request;

import com.example.animal.domain.enums.SexType;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public record FilterAnimalRequest(
    String startYear,
    String endYear,
    SexType sexCd,
    @DateTimeFormat(iso = ISO.DATE) LocalDate startDate,
    @DateTimeFormat(iso = ISO.DATE) LocalDate endDate,
    Long districtId,
    Long cityProvinceId

) {

}
