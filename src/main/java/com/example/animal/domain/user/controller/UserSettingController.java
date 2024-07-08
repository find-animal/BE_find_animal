package com.example.animal.domain.user.controller;


import com.example.animal.domain.email.dto.request.CodeRequest;
import com.example.animal.domain.email.service.EmailService;
import com.example.animal.domain.user.dto.request.AddUserRequest;
import com.example.animal.domain.user.dto.request.UpdatePasswordRequest;
import com.example.animal.domain.user.dto.response.UserResponse;
import com.example.animal.domain.user.service.UserSettingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User설정 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("${server.api.prefix}/user")
public class UserSettingController {

  private final UserSettingService userSettingService;

  @Operation(summary = "유저 비밀번호 변경")
  @PatchMapping("/password")
  public ResponseEntity<Void> updatePassword(@Valid @RequestBody UpdatePasswordRequest request) {
    userSettingService.updatePassword(request);

    return ResponseEntity.ok().build();
  }

  @Operation(summary = "아이디 찾기")
  @GetMapping("/id")
  public ResponseEntity<UserResponse> findId(@ParameterObject @ModelAttribute CodeRequest codeRequest) {
    UserResponse userResponse = userSettingService.findByEmail(codeRequest);

    return ResponseEntity.ok()
        .body(userResponse);
  }
}
