package com.example.animal.domain.user.service;

import com.example.animal.config.jwt.TokenProvider;
import com.example.animal.domain.animal.repository.AnimalRepository;
import com.example.animal.domain.user.dto.request.AddUserRequest;
import com.example.animal.domain.user.dto.request.LoginRequest;
import com.example.animal.domain.user.dto.response.LoginResponse;
import com.example.animal.domain.user.dto.response.UserResponse;
import com.example.animal.domain.user.entity.User;
import com.example.animal.domain.user.repository.UserRepository;
import com.example.animal.exception.RestApiException;
import com.example.animal.exception.user.UserErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

  private final UserRepository userRepository;
  private final TokenProvider tokenProvider;

  //회원가입
  @Transactional
  public UserResponse save(AddUserRequest dto) {
    //db에 해당 이메일이 존재하는 지 확인
    userRepository.findById(dto.id())
        .ifPresent(user -> {
          throw new RestApiException(UserErrorCode.ID_ALREADY_EXISTS);
        });

    User savedUser = userRepository.save(AddUserRequest.toEntity(dto));

    return UserResponse.fromEntity(savedUser);
  }

  //로그인 임시임 추후 security로 변경할 예정
  public LoginResponse login(LoginRequest dto) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    User user = userRepository.findById(dto.id())
        .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_FOUND_USER));

    if (!encoder.matches(dto.password(), user.getPassword())) {
      throw new RestApiException(UserErrorCode.INVALID_PASSWORD);
    }

    String token = tokenProvider.generateToken(user);

    return LoginResponse.builder()
        .jwt(token)
        .build();
  }

}