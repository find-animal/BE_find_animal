package com.example.animal.domain.user.dto.response;

import lombok.Builder;

@Builder
public record SignOutResponse(
    boolean isSuccess
) {

}
