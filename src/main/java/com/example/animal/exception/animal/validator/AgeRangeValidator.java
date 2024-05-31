package com.example.animal.exception.animal.validator;

import com.example.animal.domain.animal.dto.request.FilterAnimalRequest;
import com.example.animal.exception.RestApiException;
import com.example.animal.exception.animal.AnimalErrorCode;

public class AgeRangeValidator {
  public static void validate(FilterAnimalRequest filterAnimalRequest) {
    String startYear = filterAnimalRequest.startYear();
    String endYear = filterAnimalRequest.endYear();

    boolean hasStartYear = startYear != null;
    boolean hasEndYear = endYear != null;

    if (hasStartYear ^ hasEndYear) {
      throw new RestApiException(AnimalErrorCode.MISSING_REQUIRED_FIELDS);
    }

    if (hasStartYear && Integer.parseInt(startYear) >= Integer.parseInt(endYear)) {
      throw new RestApiException(AnimalErrorCode.INVALID_DATE_RANGE);
    }
  }
}
