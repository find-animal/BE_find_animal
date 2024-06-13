package com.example.animal.domain.user.service;

import com.example.animal.domain.animal.entity.Animal;
import com.example.animal.domain.animal.repository.AnimalRepository;
import com.example.animal.domain.user.dto.request.FavoriteAnimalRequest;
import com.example.animal.domain.user.dto.response.FavoriteAnimalResponse;
import com.example.animal.domain.user.entity.User;
import com.example.animal.domain.user.repository.UserRepository;
import com.example.animal.exception.RestApiException;
import com.example.animal.exception.common.CommonErrorCode;
import com.example.animal.exception.user.UserErrorCode;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserFavoriteService {

  private final UserRepository userRepository;
  private final AnimalRepository animalRepository;

  //관심동물 저장
  @Transactional
  public FavoriteAnimalResponse saveFavoriteAnimal(
      FavoriteAnimalRequest favoriteAnimalRequest) {
    //db에 user가 존재하는지 확인
    User user = userRepository.findById(favoriteAnimalRequest.userId())
        .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_FOUND_USER));
    //db에 해당 동물의 id값을 가지고 있는지 확인
    Animal animal = animalRepository.findById(favoriteAnimalRequest.animalId())
        .orElseThrow(() -> new RestApiException(CommonErrorCode.NO_MATCHING_RESOURCE));
    //이미 추가되어있는지 확인
    List<Long> savedAnimals = parseFavoriteAnimal(user.getFavoriteAnimal());

    if (savedAnimals.contains(animal.getId())) {
      throw new RestApiException(UserErrorCode.ANIMAL_ALREADY_EXISTS);
    }
    //관심동물을 저장
    user.setFavoriteAnimal(favoriteAnimalRequest.animalId());
    //저장된 list를 출력
    List<Long> favoriteAnimals = parseFavoriteAnimal(user.getFavoriteAnimal());

    return FavoriteAnimalResponse.builder()
        .favoriteAnimals(favoriteAnimals)
        .build();
  }

  //관심동물삭제
  @Transactional
  public FavoriteAnimalResponse deleteFavoriteAnimal(FavoriteAnimalRequest favoriteAnimalRequest) {
    //db에 유저가 존재하는지 파악
    User user = userRepository.findById(favoriteAnimalRequest.userId())
        .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_FOUND_USER));

    String updatedList = user.getFavoriteAnimal()
        .replace(favoriteAnimalRequest.animalId() + ",", "");
    user.setFavoriteAnimal(updatedList);

    List<Long> favoriteAnimals = parseFavoriteAnimal(user.getFavoriteAnimal());

    return FavoriteAnimalResponse.builder()
        .favoriteAnimals(favoriteAnimals)
        .build();
  }

  private List<Long> parseFavoriteAnimal(String favoriteAnimal) {
    return Arrays.stream(favoriteAnimal.split(","))
        .filter(s -> !s.isEmpty())
        .map(Long::valueOf)
        .toList();
  }

}
