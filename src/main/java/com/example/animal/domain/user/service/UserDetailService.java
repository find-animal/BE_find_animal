package com.example.animal.domain.user.service;

import com.example.animal.domain.user.entity.User;
import com.example.animal.domain.user.repository.UserRepository;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {

  private final UserRepository userRepository;

  //사용자 이름으로 사용자 정보를 가져오는 메소드
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByNickname(email)
        .orElseThrow(() -> new IllegalArgumentException("Not Found User Email"));

    return new org.springframework.security.core.userdetails.User(user.getNickname(),
        user.getPassword(), Collections.emptyList());

  }
}
