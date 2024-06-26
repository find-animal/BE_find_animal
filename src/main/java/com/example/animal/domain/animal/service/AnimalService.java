package com.example.animal.domain.animal.service;

import com.example.animal.domain.animal.dto.request.AnimalSearchCondition;
import com.example.animal.domain.animal.dto.response.AnimalListOpenApiResponse;
import com.example.animal.domain.animal.dto.response.AnimalListResponse;
import com.example.animal.domain.animal.dto.response.AnimalPageResponse;
import com.example.animal.domain.animal.dto.response.AnimalResponse;
import com.example.animal.domain.animal.entity.Animal;
import com.example.animal.domain.animal.repository.AnimalRepository;
import com.example.animal.domain.shelter.entity.Shelter;
import com.example.animal.domain.shelter.repository.ShelterRepository;
import com.example.animal.domain.user.entity.User;
import com.example.animal.domain.user.repository.UserRepository;
import com.example.animal.exception.RestApiException;
import com.example.animal.exception.common.CommonErrorCode;
import com.example.animal.exception.user.UserErrorCode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AnimalService {

  private final AnimalRepository animalRepository;
  private final ShelterRepository shelterRepository;
  private final UserRepository userRepository;

  //관심동물 조회
  @Transactional
  public List<AnimalListResponse> findFavoriteAnimals(Long userId) {
    //user조회
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_FOUND_USER));

    //관심동물 아이디 리스트 조회
    List<Long> favoriteIds = parseFavoriteAnimal(user.getFavoriteAnimal());

    //아이디 리스트를 통해 유기동물 조회 후 출력
    List<Animal> animals = new ArrayList<>();

    for(Long id : favoriteIds) {
      animals.add(animalRepository.findById(id)
          .orElseThrow(() -> new RestApiException(CommonErrorCode.NO_MATCHING_RESOURCE)));
    }

    return animals.stream().map(AnimalListResponse::fromEntity).toList();
  }

  //보호소 동물 저장
  public List<Animal> saveAll(AnimalListOpenApiResponse response, String careRegNo) {
    Shelter shelter = shelterRepository.findByCareRegNo(careRegNo)
        .orElseThrow(() -> new IllegalArgumentException("Not Found Shelter"));

    if (response == null) {
      return Collections.emptyList();
    }

    return animalRepository.saveAll(response.getAnimals().stream()
        .map((animalResponse) -> animalResponse.toEntity(shelter))
        .toList());
  }

  //보호소 동물 상세 정보 조회
  public AnimalResponse getAnimalDetail(Long id) {
    Animal animal = animalRepository.findById(id)
        .orElseThrow(() -> new RestApiException(CommonErrorCode.NO_MATCHING_RESOURCE));

    return AnimalResponse.fromEntity(animal);
  }

  //보호소 동물 필터
  public AnimalPageResponse getFilteredAnimalList(AnimalSearchCondition animalSearchCondition,
      Pageable pageable) {
    Page<Animal> animalPage = animalRepository.findAnimalByFilter(animalSearchCondition, pageable);
    List<AnimalListResponse> animals = animalPage.getContent().stream()
        .map((AnimalListResponse::fromEntity))
        .toList();

    return AnimalPageResponse.of(animals, animalPage);
  }

  private List<Long> parseFavoriteAnimal(String favoriteAnimal) {
    return Arrays.stream(favoriteAnimal.split(","))
        .filter(s -> !s.isEmpty())
        .map(Long::valueOf)
        .toList();
  }
}
