package com.example.animal.domain.user.controller;

import com.example.animal.domain.user.dto.request.AddFavoriteAnimalRequest;
import com.example.animal.domain.user.dto.response.FavoriteAnimalResponse;
import com.example.animal.domain.user.dto.request.AddUserRequest;
import com.example.animal.domain.user.dto.request.LoginRequest;
import com.example.animal.domain.user.dto.response.LoginResponse;
import com.example.animal.domain.user.dto.response.UserResponse;
import com.example.animal.domain.user.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth API", description = "Auth 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("${server.api.prefix}/auth")
public class AuthController {

  private final AuthService authService;

  @Operation(summary = "회원가입")
  @PostMapping("/signup")
  public ResponseEntity<UserResponse> signup(@Valid @RequestBody AddUserRequest request) {
    UserResponse response = authService.save(request);

    return ResponseEntity.ok()
        .body(response);
  }

  @Operation(summary = "로그인", description = "임시용 임")
  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
    LoginResponse response = authService.login(request);
    return ResponseEntity.ok()
        .body(response);
  }
}
