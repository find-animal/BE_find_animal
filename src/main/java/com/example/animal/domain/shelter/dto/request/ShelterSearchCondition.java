package com.example.animal.domain.shelter.dto.request;

import java.util.List;

public record ShelterSearchCondition(
        int pageNo
        , List<Long> cityProvinceId
) {
}
