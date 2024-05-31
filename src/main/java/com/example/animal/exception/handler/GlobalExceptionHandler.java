package com.example.animal.exception.handler;

import com.example.animal.exception.animal.AnimalException;
import com.example.animal.exception.dto.response.CustomErrorResponse;
import com.example.animal.exception.enums.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(AnimalException.class)
  public ResponseEntity<CustomErrorResponse> handleAnimalException(AnimalException e) {
    ErrorCode errorCode = e.getErrorCode();
    CustomErrorResponse errorResponse = CustomErrorResponse.builder()
        .code(errorCode.getCode())
        .status(errorCode.getStatus())
        .message(errorCode.getMessage())
        .build();

    return ResponseEntity.status(errorCode.getStatus())
        .body(errorResponse);
  }

}
