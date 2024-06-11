package com.example.animal.config.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

  private final TokenProvider tokenProvider;
  private final static String HEADER_AUTHORIZATION = "Authorization";
  private final static String TOKEN_PREFIX = "Bearer ";

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    //요청 헤더의 Authorization 키의 값 조회
    String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
    //가져온 값에서 접두사 제거
    String token = getAccessToken(authorizationHeader);
    //가져온 토큰이 유효한지 확인하고, 유효하다면 인증 정보를 설정
    //임시로 null이면은 그냥 넘어가게 해놈 추후 빼야함
    if (token != null && tokenProvider.validToken(token)) {
      Authentication authentication = tokenProvider.getAuthentication(token);
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filterChain.doFilter(request, response);

  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    //로그인 회원가입시에는 이전에 토큰이 없기에 토큰 필터를 거치지 않고 sercurity로 넘어감
    //swagger도 추가해줘야함
    String excludePath = "/api/v1/user";
    String path = request.getRequestURI();
    return path.startsWith(excludePath);
  }

  private String getAccessToken(String authorizationHeader) {
    if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
      return authorizationHeader.substring(TOKEN_PREFIX.length());
    }
    return null;
  }
}
