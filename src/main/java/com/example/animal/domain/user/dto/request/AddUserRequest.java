package com.example.animal.domain.user.dto.request;

import com.example.animal.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public record AddUserRequest(
    @Schema(description = "이메일", example = "abc123@mail.com")
    @NotBlank(message = "아이디를 입력해주세요.")
    @Size(min = 4, message = "아이디는 최소 4글자 이상이어야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "아이디는 영문자와 숫자만 사용할 수 있습니다.")
    String id,
    @Schema(description = "비밀번호", example = "대소문자, 특수문자 포함 최소 8글자")
    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    String password
) {

  public static User toEntity(AddUserRequest request) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    return User.builder()
        .id(request.id)
        .password(encoder.encode(request.password))
        .build();
  }
}
