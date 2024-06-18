package com.example.animal.domain.email.service;

import com.example.animal.domain.email.dto.EmailMessage;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@RequiredArgsConstructor
public class EmailService {

  private final JavaMailSender javaMailSender;
  private final SpringTemplateEngine templateEngine;

  public void sendMail(EmailMessage emailMessage, String type) {

    MimeMessage mimeMessage = javaMailSender.createMimeMessage();

    try {
      MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
      mimeMessageHelper.setTo(emailMessage.to()); // 메일 수신자
      mimeMessageHelper.setSubject(emailMessage.subject()); // 메일 제목
      mimeMessageHelper.setText(setContext(type), true); // 메일 본문 내용, HTML 여부
      javaMailSender.send(mimeMessage);

    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }
  }

  //thymeleaf를 통한 html 적용
  private String setContext(String type) {
    Context context = new Context();
    return templateEngine.process(type, context);
  }
}
