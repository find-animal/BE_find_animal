package com.example.animal.domain.email.controller;

import com.example.animal.domain.email.dto.EmailMessage;
import com.example.animal.domain.email.dto.request.CodeRequest;
import com.example.animal.domain.email.dto.request.EmailRequest;
import com.example.animal.domain.email.dto.response.EmailResponse;
import com.example.animal.domain.email.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${server.api.prefix}/send-mail")
public class EmailController {

  private final EmailService emailService;

  @Operation(summary = "이메일 인증코드 확인")
  @GetMapping("/checkCode")
  public ResponseEntity<EmailResponse> checkCode(
      @ParameterObject @ModelAttribute CodeRequest codeRequest) {
    EmailResponse response = emailService.checkCode(codeRequest);

    return ResponseEntity.ok()
        .body(response);
  }

  @Operation(summary = "비밀번호 찾기 및 새로운 비밀번호 설정")
  @PostMapping("/password")
  public ResponseEntity<Void> sendPasswordMail(@RequestBody EmailRequest emailRequest) {
    EmailMessage emailMessage = EmailMessage.builder()
        .to(emailRequest.email())
        .subject("[FIND_ANIMAL] 새로운 비밀번호 설정")
        .build();

    emailService.sendMail(emailMessage, "password");

    return ResponseEntity.ok().build();
  }

  @Operation(summary = "이메일 인증 번호")
  @PostMapping("/email")
  public ResponseEntity<EmailResponse> sendEmailCode(@RequestBody EmailRequest emailRequest) {
    EmailMessage emailMessage = EmailMessage.builder()
        .to(emailRequest.email())
        .subject("[FIND_ANIMAL] 회원가입 이메일 인증 코드 발송")
        .build();

    EmailResponse emailResponse = emailService.sendMail(emailMessage, "email");

    return ResponseEntity.ok()
        .body(emailResponse);
  }

}
