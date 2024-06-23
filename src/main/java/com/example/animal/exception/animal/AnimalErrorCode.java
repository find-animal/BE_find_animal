package com.example.animal.exception.animal;

import com.example.animal.exception.enums.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AnimalErrorCode implements ErrorCode {
  MISSING_REQUIRED_FIELDS(1000,400,"Both startYear and endYear must be present or absent"),
  INVALID_AGE_RANGE(1001,400,"StartYear must be less than endYear"),
  ;
  private final int code;
  private final int status;
  private final String defaultMessage;
  private String message;

  public String getMessage() {
    return (message != null) ? message : defaultMessage;
  }

  @Override
  public void setMessage(String message) {
    this.message = message;
  }
}
