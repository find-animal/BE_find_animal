package com.example.animal.domain.user.dto.request;

import com.example.animal.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public record AddUserRequest(
    @Schema(description = "이메일", example = "abc123@mail.com")
    String email,
    @Schema(description = "비밀번호", example = "대소문자, 특수문자 포함 최소 8글자")
    String password
) {

  public static User toEntity(AddUserRequest request) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    return User.builder()
        .email(request.email)
        .password(encoder.encode(request.password))
        .build();
  }
}
