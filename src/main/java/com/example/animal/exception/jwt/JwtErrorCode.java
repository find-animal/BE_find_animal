package com.example.animal.exception.jwt;

import com.example.animal.exception.enums.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JwtErrorCode implements ErrorCode {
  INVALID_TOKEN(3000,401,"Invalid JWT Token"),
  EXPIRED_TOKEN(3001,401,"Expired JWT Token"),
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
