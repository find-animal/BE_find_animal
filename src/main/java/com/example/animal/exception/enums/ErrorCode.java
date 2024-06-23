package com.example.animal.exception.enums;

public interface ErrorCode {

  int getCode();

  int getStatus();

  String getMessage();

  void setMessage(String message);
}
