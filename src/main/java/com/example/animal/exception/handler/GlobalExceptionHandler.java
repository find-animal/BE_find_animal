package com.example.animal.exception.handler;

import com.example.animal.exception.RestApiException;
import com.example.animal.exception.common.CommonErrorCode;
import com.example.animal.exception.dto.response.CustomErrorResponse;
import com.example.animal.exception.enums.ErrorCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(RestApiException.class)
  public ResponseEntity<Object> handleCustomException(RestApiException e) {
    ErrorCode errorCode = e.getErrorCode();
    return handleExceptionInternal(errorCode);
  }

  @ExceptionHandler({MethodArgumentTypeMismatchException.class})
  public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
      MethodArgumentTypeMismatchException e) {
    ErrorCode errorCode = CommonErrorCode.INVALID_PARAMETER;
    return handleExceptionInternal(errorCode);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    ErrorCode errorCode = CommonErrorCode.INVALID_PARAMETER;
    return handleExceptionInternal(errorCode);
  }

  @ExceptionHandler({Exception.class})
  public ResponseEntity<Object> handleAllException(Exception ex) {
    System.out.println(ex.getMessage());
    ErrorCode errorCode = CommonErrorCode.INTERNAL_SERVER_ERROR;
    return handleExceptionInternal(errorCode);
  }

  private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode) {
    return ResponseEntity.status(errorCode.getStatus())
        .body(makeErrorResponse(errorCode));
  }

  private CustomErrorResponse makeErrorResponse(ErrorCode errorCode) {
    return CustomErrorResponse.builder()
        .status(errorCode.getStatus())
        .code(errorCode.getCode())
        .message(errorCode.getMessage())
        .build();
  }
}
