package com.example.animal.domain.user.dto.response;

import com.example.animal.domain.user.entity.User;
import lombok.Builder;

@Builder
public record SignupResponse(
    String nickname,
    String password
) {

  public static SignupResponse fromEntity(User user) {
    return SignupResponse.builder()
        .nickname(user.getNickname())
        .password(user.getPassword())
        .build();
  }
}
