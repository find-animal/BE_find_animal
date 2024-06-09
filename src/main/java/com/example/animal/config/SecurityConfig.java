package com.example.animal.config;

import com.example.animal.domain.user.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

  private final UserDetailService userDetailService;

  private String[] whiteList = {"/swagger", "/swagger-ui.html", "/swagger-ui/**", "/api-docs",
      "/api-docs/**", "/v3/api-docs/**", "api/v1/animals/**","api/v1/user/**","/api/v1/cityProvince","/open-api/**"};

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable) //rest api이므로 csrf 보안이 필요 없으므로 disable 처리
        .formLogin(AbstractHttpConfigurer::disable) //폼로그인 비활성화
        .authorizeHttpRequests((authorize) ->
            authorize.requestMatchers(whiteList).permitAll()
                .anyRequest().authenticated()
        )
        .sessionManagement(config -> config.sessionCreationPolicy(
            SessionCreationPolicy.STATELESS))//jwt로 인증을 진행하므로 세션은 stateless
    ;
    //jwt 필터를 추후에 추가해줘야한다.

    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager() throws Exception {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailService);
    authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
    return new ProviderManager(authenticationProvider);
  }

  @Bean //패스워드 인코더
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }


}
