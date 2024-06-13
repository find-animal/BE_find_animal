package com.example.animal.domain.user.service;

import com.example.animal.domain.user.repository.UserRepository;
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
  public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
    return userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Not Found User"));
  }
}
