package com.example.animal.exception.animal.validator;

import com.example.animal.domain.animal.dto.request.AnimalSearchCondition;
import com.example.animal.exception.RestApiException;
import com.example.animal.exception.animal.AnimalErrorCode;

public class AnimalValidator {

  public static void ageValidate(AnimalSearchCondition animalSearchCondition) {
    String startYear = animalSearchCondition.startYear();
    String endYear = animalSearchCondition.endYear();

    boolean hasStartYear = startYear != null;
    boolean hasEndYear = endYear != null;

    if (hasStartYear ^ hasEndYear) {
      throw new RestApiException(AnimalErrorCode.MISSING_REQUIRED_FIELDS);
    }

    if (hasStartYear && Integer.parseInt(startYear) >= Integer.parseInt(endYear)) {
      throw new RestApiException(AnimalErrorCode.INVALID_AGE_RANGE);
    }
  }
}
