package com.example.animal.domain.user.dto.response;

import com.example.animal.domain.user.entity.User;
import lombok.Builder;

@Builder
public record SignupResponse(
    String email,
    String password
) {
  public static SignupResponse fromEntity(User user){
    return SignupResponse.builder()
        .email(user.getEmail())
        .password(user.getPassword())
        .build();
  }
}
