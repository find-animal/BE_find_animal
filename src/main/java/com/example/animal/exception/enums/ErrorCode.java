package com.example.animal.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

  RESOURCE_NOT_FOUND(9999, 500, "존재하지 않는 리소스입니다.");
  private final int code;
  private final int status;
  private final String message;
}
