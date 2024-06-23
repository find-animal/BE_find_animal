package com.example.animal.exception.email;

import com.example.animal.exception.enums.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmailErrorCode implements ErrorCode {

  CODE_IS_INVALID(3000, 401, "잘못된 인증번호 입니다."),
  EMAIL_SEND_ERROR(3000,500,"이메일 전송에 실패했습니다."),
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

