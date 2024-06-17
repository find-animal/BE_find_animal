package com.example.animal.domain.user.controller;

import com.example.animal.domain.user.dto.request.UpdatePasswordRequest;
import com.example.animal.domain.user.dto.response.UserResponse;
import com.example.animal.domain.user.service.UserSettingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유저 설정 API", description = "유저 설정 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("${server.api.prefix}/user")
public class UserSettingController {

  private final UserSettingService userSettingService;

  //비밀번호 변경
  @Operation(summary = "비밀번호 변경")
  @PatchMapping("/password/{userId}")
  public ResponseEntity<UserResponse> updatePassword(
      @PathVariable(name = "userId") Long userId,
      @RequestBody @Valid UpdatePasswordRequest updatePasswordRequest
  ) {
    UserResponse response = userSettingService.updatePassword(userId, updatePasswordRequest);

    return ResponseEntity.ok()
        .body(response);
  }
}
