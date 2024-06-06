package com.example.animal.domain.user.service;

import com.example.animal.domain.user.dto.request.AddUserRequest;
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

}
