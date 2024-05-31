package com.example.animal.exception.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import lombok.Builder;
import org.springframework.validation.FieldError;

@Builder
public record CustomErrorResponse(
    Integer status,
    Integer code,
    String message,
    @JsonInclude(Include.NON_EMPTY) //errors가 없다면 응답으로 내려가지 않게 설정
    List<ValidationError> errors
) {


  //@Valid를 사용했을 때 에러가 발생한 경우 어느 필드에서 에러가 발생했는지 확인하기 위한 record
  @Builder
  public record ValidationError(String field, String message) {

    public static ValidationError of(final FieldError fieldError) {
      return ValidationError.builder()
          .field(fieldError.getField())
          .message(fieldError.getDefaultMessage())
          .build();
    }
  }
}