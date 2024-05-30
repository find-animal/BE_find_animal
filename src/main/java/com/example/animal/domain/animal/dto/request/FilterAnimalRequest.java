package com.example.animal.domain.animal.dto.request;

import com.example.animal.domain.enums.SexType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public record FilterAnimalRequest(
    @Schema(description = "태어난 연도 (시작)", example = "2022")
    String startYear,
    @Schema(description = "태어난 연도 (끝 - 1)", example = "2023")
    String endYear,
    @Schema(description = "성별 코드", example = "M")
    SexType sexCd,
    @Schema(description = "필터링할 공고일의 시작", example = "2024-05-30")
    @DateTimeFormat(iso = ISO.DATE) LocalDate startDate,
    @Schema(description = "필터링할 공고일의 마지막", example = "2024-05-31")
    @DateTimeFormat(iso = ISO.DATE) LocalDate endDate,
    @Schema(description = "시군구 코드 ID 여러개 가능", example = "2, 3")
    List<Long> districtIds,
    @Schema(description = "시도 코드 ID 여러개 가능", example = "18 , 19")
    List<Long> cityProvinceIds

) {

}
