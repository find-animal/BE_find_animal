package com.example.animal.domain.user.dto.request;

import com.example.animal.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public record AddUserRequest(
    @Schema(description = "아이디", example = "abc1")
    @NotBlank(message = "아이디를 입력해주세요.")
    @Size(min = 4, message = "아이디는 최소 4글자 이상이어야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "아이디는 영문자와 숫자만 사용할 수 있습니다.")
    String id,
    @Schema(description = "비밀번호", example = "대소문자, 특수문자 포함 최소 8글자")
    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    String password,

    @Schema(description = "이메일")
    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식을 맞춰주세요.")
    String email,

    @Schema(description = "인증번호")
    @NotBlank(message = "인증번호를 입력해주세요")
    String code
) {

  public static User toEntity(AddUserRequest request, BCryptPasswordEncoder passwordEncoder) {

    return User.builder()
        .id(request.id)
        .password(passwordEncoder.encode(request.password))
        .email(request.email())
        .build();
  }
}
