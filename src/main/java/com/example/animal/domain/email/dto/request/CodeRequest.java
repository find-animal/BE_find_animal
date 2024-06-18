package com.example.animal.domain.email.dto.request;

import lombok.Builder;

@Builder
public record CodeRequest(
    String email,
    String code
) {

}
