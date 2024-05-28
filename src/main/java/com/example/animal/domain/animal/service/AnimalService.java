package com.example.animal.domain.animal.service;

import com.example.animal.domain.animal.dto.request.FilterAnimalRequest;
import com.example.animal.domain.animal.dto.response.AnimalListOpenApiResponse;
import com.example.animal.domain.animal.dto.response.AnimalListResponse;
import com.example.animal.domain.animal.dto.response.AnimalResponse;
import com.example.animal.domain.animal.dto.response.QAnimalListResponse;
import com.example.animal.domain.animal.entity.Animal;
import com.example.animal.domain.animal.entity.QAnimal;
import com.example.animal.domain.animal.repository.AnimalRepository;
import com.example.animal.domain.shelter.entity.Shelter;
import com.example.animal.domain.shelter.repository.ShelterRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AnimalService {

  private final AnimalRepository animalRepository;
  private final ShelterRepository shelterRepository;
  private final JPAQueryFactory queryFactory;

  //보호소 동물 저장
  public List<Animal> saveAll(AnimalListOpenApiResponse response, String careRegNo) {
    Shelter shelter = shelterRepository.findByCareRegNo(careRegNo)
        .orElseThrow(() -> new IllegalArgumentException("Not Found Shelter"));

    return animalRepository.saveAll(response.getAnimals().stream()
        .map((animal) -> animal.toEntity(shelter))
        .toList());
  }

  //보호소 동물 상세 정보 조회
  public AnimalResponse getAnimalDetail(Long id) {
    Animal animal = animalRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Not Found Animal"));

    return new AnimalResponse(animal);
  }

  //보호소 동물 필터
  public List<AnimalListResponse> getFilteredAnimalList(FilterAnimalRequest filterAnimalRequest,
      Pageable pageable) {
    QAnimal animal = QAnimal.animal;
    BooleanBuilder whereClause = new BooleanBuilder();

    if (filterAnimalRequest.getSexCd() != null) {
      whereClause.and(animal.sexCd.eq(filterAnimalRequest.getSexCd()));
    }
    if (filterAnimalRequest.getDistrictId() != null) {
      whereClause.and(animal.shelter.district.id.eq(filterAnimalRequest.getDistrictId()));
    }
    if (filterAnimalRequest.getCityProvinceId() != null) {
      whereClause.and(animal.shelter.cityProvince.id.eq(filterAnimalRequest.getCityProvinceId()));
    }
    if (filterAnimalRequest.getStartYear() != null && filterAnimalRequest.getEndYear() != null) {
      whereClause.and(animal.age.between(filterAnimalRequest.getStartYear(), filterAnimalRequest.getEndYear()));
    }
    if (filterAnimalRequest.getStartDate() != null && filterAnimalRequest.getEndDate() != null) {
      whereClause.and(animal.noticeEdt.between(filterAnimalRequest.getStartDate(), filterAnimalRequest.getEndDate()));
    }

    List<AnimalListResponse> animals = queryFactory
        .select(new QAnimalListResponse(
            animal.id,
            animal.fileName,
            animal.animalBreed,
            animal.happenPlace,
            animal.happenDt,
            animal.age,
            animal.sexCd,
            animal.weight,
            animal.noticeSdt,
            animal.noticeEdt
        ))
        .from(animal)
        .where(whereClause)
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();
    return animals;
  }
}
