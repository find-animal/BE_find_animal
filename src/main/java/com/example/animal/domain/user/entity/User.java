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
  private Long userId;

  private String id;

  private String password;

  private String favoriteAnimal = "";

  private String favoriteShelter = "";

  private String email;

  @Builder
  public User(String id, String password, String email) {
    this.id = id;
    this.password = password;
    this.email = email;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(("ROLE_USER")));
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setFavoriteAnimal(Long id) {
    this.favoriteAnimal += id + ",";
  }

  public void setFavoriteAnimal(String list) {
    this.favoriteAnimal = list;
  }

  public void setFavoriteShelter(Long id) {
    this.favoriteShelter += id + ",";
  }

  public void setFavoriteShelter(String list) {
    this.favoriteShelter = list;
  }

  @Override
  public String getUsername() {
    return id;
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
