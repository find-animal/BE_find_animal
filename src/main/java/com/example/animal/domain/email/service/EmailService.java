package com.example.animal.domain.email.service;

import com.example.animal.domain.email.dto.EmailMessage;
import com.example.animal.domain.email.entity.Email;
import com.example.animal.domain.email.repository.EmailRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.Random;
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
  private final EmailRepository emailRepository;

  public void sendMail(EmailMessage emailMessage, String type) {

    MimeMessage mimeMessage = javaMailSender.createMimeMessage();

    try {
      String code = createCode();
      MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
      mimeMessageHelper.setTo(emailMessage.to()); // 메일 수신자
      mimeMessageHelper.setSubject(emailMessage.subject()); // 메일 제목
      mimeMessageHelper.setText(setContext(type, code), true); // 메일 본문 내용, HTML 여부
      javaMailSender.send(mimeMessage);

      emailRepository.save(Email.builder()
          .email(emailMessage.to())
          .code(code)
          .build());

    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }
  }

  // 인증번호 생성 메서드
  private String createCode() {
    Random random = new Random();
    StringBuffer key = new StringBuffer();

    for (int i = 0; i < 8; i++) {
      int index = random.nextInt(4);

      switch (index) {
        case 0:
          key.append((char) (random.nextInt(26) + 97));
          break;
        case 1:
          key.append((char) (random.nextInt(26) + 65));
          break;
        default:
          key.append(random.nextInt(9));
      }
    }
    return key.toString();
  }

  //thymeleaf를 통한 html 적용
  private String setContext(String type, String code) {
    Context context = new Context();
    context.setVariable("code", code);
    return templateEngine.process(type, context);
  }
}
