package com.example.animal.domain.user.controller;


import com.example.animal.domain.user.dto.request.AddUserRequest;
import com.example.animal.domain.user.service.UserSettingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User설정 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("${server.api.prefix}/user")
public class UserSettingController {

  private final UserSettingService userSettingService;

  @Operation(summary = "유저 비밀번호 변경")
  @PatchMapping("/password")
  public ResponseEntity<Void> updatePassword(@Valid @RequestBody AddUserRequest request) {
    userSettingService.updatePassword(request);

    return ResponseEntity.ok().build();
  }
}
