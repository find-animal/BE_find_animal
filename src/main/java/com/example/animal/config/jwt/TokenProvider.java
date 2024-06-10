package com.example.animal.config.jwt;

import com.example.animal.domain.user.entity.User;
import com.example.animal.domain.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenProvider {

  private final JwtProperties jwtProperties;
  private final UserRepository userRepository;

  //jwt 토큰 생성
  public String generateToken(User user) {
    Date now = new Date();
    String jwt = Jwts.builder()
        .issuer(jwtProperties.getIssuer())
        .subject(user.getId().toString())
        .issuedAt(now)
        .signWith(getSigningKey())
        .expiration(new Date(now.getTime() + jwtProperties.getExpirationDate())) //현재시간 + 1일 임시임
        .id(UUID.randomUUID().toString())
        .claims(getClaimsMap(user))
        .compact();
    return jwt;
  }

  //jwt 토큰 유효성 검증
  public boolean validToken(String token) {
    try {
      Jwts.parser()
          .verifyWith(getSigningKey())
          .build()
          .parseSignedClaims(token);
      return true;
    } catch (SecurityException | MalformedJwtException e) {
      System.out.println("Invalid JWT Token");
    } catch (ExpiredJwtException e) {
      System.out.println("Expired JWT Token");
    } catch (UnsupportedJwtException e) {
      System.out.println("Unsupported JWT Token");
    } catch (IllegalArgumentException e) {
      System.out.println("JWT claims string is empty");
    }
    return false;
  }

  //토큰 기반으로 인증 정보를 가져오는 메소드
  public Authentication getAuthentication(String token) {
    Claims claims = getClaims(token);
    Long userId = Long.parseLong(claims.getSubject());

    User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Not Found User"));

    return new UsernamePasswordAuthenticationToken(
        user,null,user.getAuthorities()
    );


  }

  private Claims getClaims(String token) {
    return Jwts.parser()
        .verifyWith(getSigningKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  //claims payload에 들어갈 내용 추가
  private Map<String, ?> getClaimsMap(User user) {
    Map<String, String> map = new HashMap<>();
    map.put("nickname", user.getNickname());
    map.put("password", user.getPassword());

    return map;
  }

  //signingkey 발급
  private SecretKey getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecretKey());
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
