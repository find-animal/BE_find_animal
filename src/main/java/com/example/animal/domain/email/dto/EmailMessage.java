package com.example.animal.domain.email.dto;

import lombok.Builder;

@Builder
public record EmailMessage(
    String to, //수신자
    String subject, //메일 제목
    String message //메일 내용
) {

}
