package com.example.animal.exception.user;

import com.example.animal.exception.enums.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {
  EMAIL_ALREADY_EXISTS(2001, 400, "이미 사용 중인 이메일입니다."),
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
