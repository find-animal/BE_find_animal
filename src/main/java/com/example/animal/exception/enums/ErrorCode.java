package com.example.animal.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  //공통
  NO_MATCHING_RESOURCE_FOUND_BY_ID(9999, 404, "db에 해당 id값을 가진 데이터는 존재하지 않습니다.");
  private final int code;
  private final int status;
  private final String message;
}
