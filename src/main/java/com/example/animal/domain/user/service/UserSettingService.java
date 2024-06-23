package com.example.animal.domain.user.service;

import com.example.animal.domain.user.dto.request.AddUserRequest;
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
  private final BCryptPasswordEncoder passwordEncoder;

  //비밀번호 변경
  @Transactional
  public void updatePassword(AddUserRequest request) {
    User user = userRepository.findById(request.id())
        .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_FOUND_USER));

    user.setPassword(passwordEncoder.encode(request.password()));
  }

}
