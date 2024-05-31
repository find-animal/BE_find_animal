package com.example.animal.exception.animal;

import com.example.animal.exception.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AnimalException extends RuntimeException {

  private ErrorCode errorCode;

}
