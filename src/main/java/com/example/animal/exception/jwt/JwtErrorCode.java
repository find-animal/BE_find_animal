package com.example.animal.exception.jwt;

import com.example.animal.exception.enums.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JwtErrorCode implements ErrorCode {
  INVALID_TOKEN(30000,401,"유효하지 않은 토큰 정보입니다."),
  EXPIRED_TOKEN(30000,401,"로그인 정보가 만료되었습니다."),
  CLAIMS_IS_EMPTY(3002,400,"JWT claims string is empty"),
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
