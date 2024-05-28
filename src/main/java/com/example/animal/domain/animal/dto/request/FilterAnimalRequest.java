package com.example.animal.domain.animal.dto.request;

import com.example.animal.domain.enums.SexType;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterAnimalRequest {

  private String startYear;
  private String endYear;
  private SexType sexCd;
  @DateTimeFormat(iso = ISO.DATE)
  private LocalDate startDate;
  @DateTimeFormat(iso = ISO.DATE)
  private LocalDate endDate;
  private Long districtId;
  private Long cityProvinceId;
}
