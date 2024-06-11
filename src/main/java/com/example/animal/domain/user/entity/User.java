package com.example.animal.domain.user.entity;

import com.example.animal.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseEntity implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "USER_ID")
  private Long id;

  private String nickname;

  private String password;

  private String favoriteAnimal = "";

  @Builder
  public User(String nickname, String password) {
    this.nickname = nickname;
    this.password = password;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(("ROLE_USER")));
  }

  public void setFavoriteAnimal(Long id) {
    this.favoriteAnimal += id + ",";
    System.out.println(this.favoriteAnimal);
  }

  @Override
  public String getUsername() {
    return nickname;
  }

  @Override //계정 만료 여부 반환
  public boolean isAccountNonExpired() {
    return true; // true면 만료되지 않음
  }

  @Override //계정 장금 여부 반환
  public boolean isAccountNonLocked() {
    return true; //true면 잠금 되지 않음
  }

  @Override //패스워드 만료 여부 반환
  public boolean isCredentialsNonExpired() {
    return true; //true면은 만료되지 않음
  }

  @Override //계정 사용 가능 여부 반환
  public boolean isEnabled() {
    return true; //true면은 사용가능
  }
}
