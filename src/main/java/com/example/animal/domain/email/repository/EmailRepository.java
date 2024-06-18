package com.example.animal.domain.email.repository;

import com.example.animal.domain.email.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {

}
