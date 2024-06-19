package com.example.animal.domain.email.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "email")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Email {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "EMAIL_ID")
  private Long emailId;

  private String email;

  private String code;


  @Builder
  public Email(String email, String code) {
    this.email = email;
    this.code = code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
