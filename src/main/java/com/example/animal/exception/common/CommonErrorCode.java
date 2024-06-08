package com.example.animal.exception.common;

import com.example.animal.exception.enums.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {
  INTERNAL_SERVER_ERROR(9000, 500, "Internal server error"),
  INVALID_PARAMETER(9001, 400, "Invalid parameter included"),
  NO_MATCHING_RESOURCE(9002, 404, "No matching resource found"),
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
