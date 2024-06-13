package com.example.animal.config.jwt;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import com.example.animal.domain.user.entity.User;
import com.example.animal.domain.user.repository.UserRepository;
import com.example.animal.exception.RestApiException;
import com.example.animal.exception.jwt.JwtErrorCode;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.time.Duration;
import java.util.Date;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.yml")
class TokenProviderTest {

  @Autowired
  private TokenProvider tokenProvider;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private JwtProperties jwtProperties;

  @BeforeEach
  void setUp() {
    userRepository.deleteAll();
  }

  @DisplayName("[성공] 토큰 발급에 성공한다.")
  @Test
  public void generateToken() {
    //given
    User testUser = userRepository.save(User.builder()
        .id("test")
        .password("test")
        .build());

    //when
    String token = tokenProvider.generateToken(testUser);

    //when
    Long userId = Long.parseLong(Jwts.parser()
        .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecretKey())))
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .getSubject());

    //then
    assertThat(userId).isEqualTo(testUser.getUserId());
  }

  @DisplayName("[실패] 유효기간이 만료된 토큰일 때 유효성 검증에 실패한다.")
  @Test
  public void validToken_invalidToken() {
    //given
    String token = JwtFactory.builder()
        .expiration(new Date(new Date().getTime() - Duration.ofDays(7).toMillis()))
        .build()
        .createToken(jwtProperties);

    //when
    Throwable thrown = catchThrowable(
        () -> tokenProvider.validToken(token)
    );

    //then
    assertThat(thrown)
        .isInstanceOf(RestApiException.class)
        .hasFieldOrPropertyWithValue("errorCode", JwtErrorCode.EXPIRED_TOKEN);
  }

  @DisplayName("[성공] 토큰 기반으로 인증정보를 가져온다.")
  @Test
  public void getAuthentication() {
    //given
    User testUser = userRepository.save(User.builder()
        .id("test")
        .password("test")
        .build());
    Map<String,Object> claims = Map.of("nickname",testUser.getId());
    String token = JwtFactory.builder()
        .subject(testUser.getUserId().toString())
        .claims(claims)
        .build()
        .createToken(jwtProperties);

    //when
    Authentication authentication = tokenProvider.getAuthentication(token);

    //then
    assertThat(((User) authentication.getPrincipal()).getUsername()).isEqualTo(claims.get("nickname"));
  }

}