package com.example.animal.domain.user.service;

import com.example.animal.domain.user.dto.request.AddUserRequest;
import com.example.animal.domain.user.dto.request.LoginRequest;
import com.example.animal.domain.user.dto.response.SignupResponse;
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
public class UserService {

  private final UserRepository userRepository;

  //회원가입
  @Transactional
  public SignupResponse save(AddUserRequest dto) {
    //db에 해당 이메일이 존재하는 지 확인
    userRepository.findByEmail(dto.email())
        .ifPresent(user ->{
          throw new RestApiException(UserErrorCode.EMAIL_ALREADY_EXISTS);
        });
    
    User savedUser = userRepository.save(AddUserRequest.toEntity(dto));

    return SignupResponse.fromEntity(savedUser);
  }

  //로그인 임시임 추후 security로 변경할 예정
  public Boolean login(LoginRequest dto) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    User user = userRepository.findByEmail(dto.email())
        .orElseThrow(() -> new IllegalArgumentException("Not Found User Email"));

    return encoder.matches(dto.password(), user.getPassword());
  }

}
