package com.example.animal.domain.user.controller;

import com.example.animal.domain.user.dto.request.AddUserRequest;
import com.example.animal.domain.user.dto.request.CheckIdRequest;
import com.example.animal.domain.user.dto.request.LoginRequest;
import com.example.animal.domain.user.dto.request.SignOutRequest;
import com.example.animal.domain.user.dto.response.CheckIdResponse;
import com.example.animal.domain.user.dto.response.EmailResponse;
import com.example.animal.domain.user.dto.response.LoginResponse;
import com.example.animal.domain.user.dto.response.SignOutResponse;
import com.example.animal.domain.user.dto.response.UserResponse;
import com.example.animal.domain.user.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth API", description = "Auth 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("${server.api.prefix}/user")
public class AuthController {

  private final AuthService authService;

  @Operation(summary = "회원탈퇴")
  @DeleteMapping("/signout")
  public ResponseEntity<SignOutResponse> signout(@Valid @RequestBody SignOutRequest request) {
    SignOutResponse response = authService.signout(request);

    return ResponseEntity.ok()
        .body(response);
  }

  @Operation(summary = "회원가입")
  @PostMapping("/signup")
  public ResponseEntity<UserResponse> signup(@Valid @RequestBody AddUserRequest request) {
    UserResponse response = authService.save(request);

    return ResponseEntity.ok()
        .body(response);
  }

  @Operation(summary = "로그인")
  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
    LoginResponse response = authService.login(request);
    return ResponseEntity.ok()
        .body(response);
  }

  @Operation(summary = "아이디 중복 체크")
  @GetMapping("/checkId")
  public ResponseEntity<CheckIdResponse> checkId(
      @ParameterObject @Valid CheckIdRequest checkIdRequest) {
    CheckIdResponse response = authService.checkId(checkIdRequest);

    return ResponseEntity.ok()
        .body(response);
  }

  @Operation(summary = "이메일 조회")
  @GetMapping("/email")
  public ResponseEntity<EmailResponse> getEmail(@RequestParam("id") String id) {
    EmailResponse emailResponse = authService.findEmail(id);

    return ResponseEntity.ok()
        .body(emailResponse);
  }
}
