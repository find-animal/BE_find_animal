package com.example.animal.domain.user.dto.response;

import com.example.animal.domain.email.entity.Email;
import com.example.animal.domain.user.entity.User;
import lombok.Builder;

@Builder
public record EmailResponse(
    String email
) {
}
