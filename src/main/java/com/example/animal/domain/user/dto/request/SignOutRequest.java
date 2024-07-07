package com.example.animal.domain.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record SignOutRequest(
    @Schema(description = "아이디")
    @NotNull(message = "아이디를 입력해주세요.")
    String id,
    @Schema(description = "비밀번호")
    @NotNull(message = "비밀번호를 입력해주세요.")
    String password,
    @Schema(description = "인증번호")
    @NotNull(message = "인증번호를 입력해주세요.")
    String code
) {

}
