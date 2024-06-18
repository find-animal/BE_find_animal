package com.example.animal.domain.email.controller;

import com.example.animal.domain.email.dto.EmailMessage;
import com.example.animal.domain.email.dto.request.EmailRequest;
import com.example.animal.domain.email.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${server.api.prefix}/email")
public class EmailController {

  private final EmailService emailService;

  @Operation(summary = "비밀번호 찾기 및 새로운 비밀번호 설정")
  @PostMapping("/password")
  public ResponseEntity<Void> sendPasswordMail(@RequestBody EmailRequest emailRequest) {
    EmailMessage emailMessage = EmailMessage.builder()
        .to(emailRequest.email())
        .subject("[FIND_ANIMAL] 새로운 비밀번호 설정")
        .build();

    emailService.sendMail(emailMessage,"password");

    return ResponseEntity.ok().build();
  }

}
