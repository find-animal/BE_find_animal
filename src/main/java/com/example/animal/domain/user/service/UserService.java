package com.example.animal.domain.user.service;

import com.example.animal.domain.user.dto.request.AddUserRequest;
import com.example.animal.domain.user.dto.request.LoginRequest;
import com.example.animal.domain.user.dto.response.SignupResponse;
import com.example.animal.domain.user.entity.User;
import com.example.animal.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;

  //회원가입
  public SignupResponse save(AddUserRequest dto) {
    //todo 이메일 비밀번호 유효성 체크
    User savedUser = userRepository.save(AddUserRequest.toEntity(dto));

    return SignupResponse.fromEntity(savedUser);
  }

  //로그인 임시임 추후 security로 변경할 예정
  public Boolean login(LoginRequest dto) {
    User user = userRepository.findByEmail(dto.email())
        .orElseThrow(() -> new IllegalArgumentException("Not Found User Email"));

    return user.getPassword().equals(dto.password());
  }

}
