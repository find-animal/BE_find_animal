package com.example.animal.exception.jwt;

import com.example.animal.exception.dto.response.CustomErrorResponse;
import com.example.animal.exception.enums.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtErrorSend {

  public static void handleException(HttpServletResponse response, ErrorCode errorCode)
      throws IOException {
    CustomErrorResponse customErrorResponse = CustomErrorResponse.builder()
        .status(errorCode.getStatus())
        .code(errorCode.getCode())
        .message(errorCode.getMessage())
        .build();

    response.setStatus(customErrorResponse.status());
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(new ObjectMapper().writeValueAsString(customErrorResponse));
  }
}
