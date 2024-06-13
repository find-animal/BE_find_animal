package com.example.animal.domain.user.dto.response;

import com.example.animal.domain.user.entity.User;
import lombok.Builder;

@Builder
public record SignupResponse(
    String id,
    String password
) {

  public static SignupResponse fromEntity(User user) {
    return SignupResponse.builder()
        .id(user.getId())
        .password(user.getPassword())
        .build();
  }
}
