package com.example.animal.domain.user.service;

import com.example.animal.config.jwt.TokenProvider;
import com.example.animal.domain.user.dto.request.AddUserRequest;
import com.example.animal.domain.user.dto.request.LoginRequest;
import com.example.animal.domain.user.dto.response.LoginResponse;
import com.example.animal.domain.user.dto.response.SignupResponse;
import com.example.animal.domain.user.entity.User;
import com.example.animal.domain.user.repository.UserRepository;
import com.example.animal.exception.RestApiException;
import com.example.animal.exception.user.UserErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;
  private final TokenProvider tokenProvider;

  //회원가입
  @Transactional
  public SignupResponse save(AddUserRequest dto) {
    //db에 해당 이메일이 존재하는 지 확인
    userRepository.findByNickname(dto.nickname())
        .ifPresent(user ->{
          throw new RestApiException(UserErrorCode.EMAIL_ALREADY_EXISTS);
        });
    
    User savedUser = userRepository.save(AddUserRequest.toEntity(dto));

    return SignupResponse.fromEntity(savedUser);
  }

  //로그인 임시임 추후 security로 변경할 예정
  public LoginResponse login(LoginRequest dto) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    User user = userRepository.findByNickname(dto.id())
        .orElseThrow(() -> new UsernameNotFoundException("Not Found User Nickname"));

    if(!encoder.matches(dto.password(), user.getPassword())) {
      throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
    }

    String token = tokenProvider.generateToken(user);

    return LoginResponse.builder()
        .jwt(token)
        .build();
  }

}
