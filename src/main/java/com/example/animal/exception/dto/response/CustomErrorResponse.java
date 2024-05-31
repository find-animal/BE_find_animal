package com.example.animal.exception.dto.response;

import lombok.Builder;

@Builder
public record CustomErrorResponse(
    Integer status,
    Integer code,
    String message
) {

}