package com.example.animal.domain.user.service;

import com.example.animal.domain.email.dto.request.CodeRequest;
import com.example.animal.domain.email.entity.Email;
import com.example.animal.domain.email.repository.EmailRepository;
import com.example.animal.domain.user.dto.request.AddUserRequest;
import com.example.animal.domain.user.dto.response.UserResponse;
import com.example.animal.domain.user.entity.User;
import com.example.animal.domain.user.repository.UserRepository;
import com.example.animal.exception.RestApiException;
import com.example.animal.exception.common.CommonErrorCode;
import com.example.animal.exception.email.EmailErrorCode;
import com.example.animal.exception.user.UserErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserSettingService {

  private final UserRepository userRepository;
  private final EmailRepository emailRepository;
  private final BCryptPasswordEncoder passwordEncoder;


  //비밀번호 변경
  @Transactional
  public void updatePassword(AddUserRequest request) {
    User user = userRepository.findById(request.id())
        .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_FOUND_USER));

    user.setPassword(passwordEncoder.encode(request.password()));
  }

  //이메일을 통한 아이디 찾기
  public UserResponse findByEmail(CodeRequest codeRequest) {
    //이메일에 들어온 코드값을 비교한다.
    Email email = emailRepository.findByEmail(codeRequest.email())
        .orElseThrow(() -> new RestApiException(EmailErrorCode.CODE_IS_INVALID));

    if (!email.getCode().equals(codeRequest.code())) {
      throw new RestApiException(EmailErrorCode.CODE_IS_INVALID);
    }

    User user = userRepository.findByEmail(codeRequest.email())
        .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_FOUND_USER));

    return UserResponse.fromEntity(user);
  }

}
