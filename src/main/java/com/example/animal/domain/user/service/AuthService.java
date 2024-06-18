package com.example.animal.domain.user.service;

import com.example.animal.config.jwt.TokenProvider;
import com.example.animal.domain.email.entity.Email;
import com.example.animal.domain.email.repository.EmailRepository;
import com.example.animal.domain.user.dto.request.AddUserRequest;
import com.example.animal.domain.user.dto.request.CheckIdRequest;
import com.example.animal.domain.user.dto.request.LoginRequest;
import com.example.animal.domain.user.dto.response.CheckIdResponse;
import com.example.animal.domain.user.dto.response.LoginResponse;
import com.example.animal.domain.user.dto.response.UserResponse;
import com.example.animal.domain.user.entity.User;
import com.example.animal.domain.user.repository.UserRepository;
import com.example.animal.exception.RestApiException;
import com.example.animal.exception.common.CommonErrorCode;
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
  private final BCryptPasswordEncoder passwordEncoder;
  private final EmailRepository emailRepository;

  //아이디 체크
  public CheckIdResponse checkId(CheckIdRequest checkIdRequest) {
    CheckIdResponse response = new CheckIdResponse(true);

    userRepository.findById(checkIdRequest.id())
        .ifPresent(user -> {
          throw new RestApiException(UserErrorCode.ID_ALREADY_EXISTS);
        });

    return response;
  }

  //회원가입
  @Transactional
  public UserResponse save(AddUserRequest dto) {
    //db에 해당 아이디가 존재하는 지 확인
    userRepository.findById(dto.id())
        .ifPresent(user -> {
          throw new RestApiException(UserErrorCode.ID_ALREADY_EXISTS);
        });

    Email email = emailRepository.findByEmail(dto.email())
        .orElseThrow(() -> new RestApiException(CommonErrorCode.NO_MATCHING_RESOURCE));

    User savedUser = userRepository.save(AddUserRequest.toEntity(dto, passwordEncoder));

    //user db에 저장이 되면 인증코드를 삭제
    emailRepository.delete(email);

    return UserResponse.fromEntity(savedUser);
  }

  //로그인
  public LoginResponse login(LoginRequest dto) {

    User user = userRepository.findById(dto.id())
        .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_FOUND_USER));

    if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
      throw new RestApiException(UserErrorCode.INVALID_PASSWORD);
    }

    String token = tokenProvider.generateToken(user);

    return LoginResponse.builder()
        .jwt(token)
        .build();
  }

}
