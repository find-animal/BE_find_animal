package com.example.animal.domain.email.repository;

import com.example.animal.domain.email.entity.Email;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {

  Optional<Email> findByEmail(String email);

}
