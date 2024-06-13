package com.example.animal.domain.user.service;

import com.example.animal.config.jwt.TokenProvider;
import com.example.animal.domain.animal.entity.Animal;
import com.example.animal.domain.animal.repository.AnimalRepository;
import com.example.animal.domain.user.dto.request.AddFavoriteAnimalRequest;
import com.example.animal.domain.user.dto.request.AddUserRequest;
import com.example.animal.domain.user.dto.request.LoginRequest;
import com.example.animal.domain.user.dto.response.FavoriteAnimalResponse;
import com.example.animal.domain.user.dto.response.LoginResponse;
import com.example.animal.domain.user.dto.response.UserResponse;
import com.example.animal.domain.user.entity.User;
import com.example.animal.domain.user.repository.UserRepository;
import com.example.animal.exception.RestApiException;
import com.example.animal.exception.common.CommonErrorCode;
import com.example.animal.exception.user.UserErrorCode;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

  private final UserRepository userRepository;
  private final TokenProvider tokenProvider;
  private final AnimalRepository animalRepository;

  //관심동물 저장
  @Transactional
  public FavoriteAnimalResponse saveFavoriteAnimal(
      AddFavoriteAnimalRequest addFavoriteAnimalRequest) {
    //db에 user가 존재하는지 확인
    User user = userRepository.findById(addFavoriteAnimalRequest.userId())
        .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_FOUND_USER));
    //db에 해당 동물의 id값을 가지고 있는지 확인
    Animal animal = animalRepository.findById(addFavoriteAnimalRequest.animalId())
        .orElseThrow(() -> new RestApiException(CommonErrorCode.NO_MATCHING_RESOURCE));
    //이미 추가되어있는지 확인
    List<Long> savedAnimals = parseFavoriteAnimal(user.getFavoriteAnimal());

    if (savedAnimals.contains(animal.getId())) {
      throw new RestApiException(UserErrorCode.ANIMAL_ALREADY_EXISTS);
    }
    //관심동물을 저장
    user.setFavoriteAnimal(addFavoriteAnimalRequest.animalId());
    //저장된 list를 출력
    List<Long> favoriteAnimals = parseFavoriteAnimal(user.getFavoriteAnimal());

    return FavoriteAnimalResponse.builder()
        .favoriteAnimals(favoriteAnimals)
        .build();
  }

  //회원가입
  @Transactional
  public UserResponse save(AddUserRequest dto) {
    //db에 해당 이메일이 존재하는 지 확인
    userRepository.findById(dto.id())
        .ifPresent(user -> {
          throw new RestApiException(UserErrorCode.EMAIL_ALREADY_EXISTS);
        });

    User savedUser = userRepository.save(AddUserRequest.toEntity(dto));

    return UserResponse.fromEntity(savedUser);
  }

  //로그인 임시임 추후 security로 변경할 예정
  public LoginResponse login(LoginRequest dto) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    User user = userRepository.findById(dto.id())
        .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_FOUND_USER));

    if (!encoder.matches(dto.password(), user.getPassword())) {
      throw new RestApiException(UserErrorCode.INVALID_PASSWORD);
    }

    String token = tokenProvider.generateToken(user);

    return LoginResponse.builder()
        .jwt(token)
        .build();
  }

  private List<Long> parseFavoriteAnimal(String favoriteAnimal) {
    return Arrays.stream(favoriteAnimal.split(","))
        .filter(s -> !s.isEmpty())
        .map(Long::valueOf)
        .toList();
  }

}
