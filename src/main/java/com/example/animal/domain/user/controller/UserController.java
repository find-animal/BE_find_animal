package com.example.animal.domain.user.controller;

import com.example.animal.domain.user.dto.request.AddUserRequest;
import com.example.animal.domain.user.dto.response.SignupResponse;
import com.example.animal.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유저 API", description = "유저 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("${server.api.prefix}/user")
public class UserController {
  private final UserService userService;

  @Operation(summary = "회원가입")
  @PostMapping("/signup")
  public ResponseEntity<SignupResponse> signup(@RequestBody AddUserRequest request) {
    SignupResponse response = userService.save(request);

    return ResponseEntity.ok()
        .body(response);
  }
}
