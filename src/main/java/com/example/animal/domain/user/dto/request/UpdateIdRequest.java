package com.example.animal.domain.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateIdRequest(
    @Schema(description = "아이디", example = "abc1")
    @NotBlank(message = "아이디를 입력해주세요.")
    @Size(min = 4, message = "아이디는 최소 4글자 이상이어야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "아이디는 영문자와 숫자만 사용할 수 있습니다.")
    String id
) {

}
