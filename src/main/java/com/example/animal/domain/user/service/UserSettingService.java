package com.example.animal.domain.user.service;

import com.example.animal.domain.user.dto.request.UpdateIdRequest;
import com.example.animal.domain.user.dto.request.UpdatePasswordRequest;
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
public class UserSettingService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  //아이디 변경
  @Transactional
  public UserResponse updateId(Long userId, UpdateIdRequest updateIdRequest) {
    //유저 찾기
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_FOUND_USER));

    //중복된 아이디 처리
    userRepository.findById(updateIdRequest.id())
        .ifPresent(savedUser -> {
          throw new RestApiException(UserErrorCode.ID_ALREADY_EXISTS);
        });

    user.setId(updateIdRequest.id());

    userRepository.save(user);

    return UserResponse.fromEntity(user);
  }

  //비밀번호 변경
  @Transactional
  public UserResponse updatePassword(Long userId, UpdatePasswordRequest updatePasswordRequest) {
    //유저 찾기
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_FOUND_USER));

    //암호화시켜서 넣어야함
    user.setPassword(bCryptPasswordEncoder.encode(updatePasswordRequest.password()));

    userRepository.save(user);

    return UserResponse.fromEntity(user);
  }
}
