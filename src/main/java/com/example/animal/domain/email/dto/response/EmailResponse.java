package com.example.animal.domain.email.dto.response;

import lombok.Builder;

@Builder
public record EmailResponse(
    boolean isSuccess
) {

}
