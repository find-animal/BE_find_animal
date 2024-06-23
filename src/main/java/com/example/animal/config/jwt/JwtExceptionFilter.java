package com.example.animal.config.jwt;

import com.example.animal.exception.RestApiException;
import com.example.animal.exception.enums.ErrorCode;
import com.example.animal.exception.jwt.JwtErrorCode;
import com.example.animal.exception.jwt.JwtErrorSend;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    try {
      filterChain.doFilter(request, response);
    } catch (RestApiException e) {
      JwtErrorSend.handleException(response, e.getErrorCode());
    }
  }
}
