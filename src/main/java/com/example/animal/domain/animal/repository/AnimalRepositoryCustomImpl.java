package com.example.animal.domain.animal.repository;

import static com.example.animal.domain.animal.entity.QAnimal.animal;

import com.example.animal.domain.animal.dto.request.FilterAnimalRequest;
import com.example.animal.domain.animal.entity.Animal;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AnimalRepositoryCustomImpl implements AnimalRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  @Override
  public List<Animal> findAnimalByFilter(FilterAnimalRequest filterAnimalRequest,
      Pageable pageable) {

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
      whereClause.and(
          animal.age.between(filterAnimalRequest.getStartYear(), filterAnimalRequest.getEndYear()));
    }
    if (filterAnimalRequest.getStartDate() != null && filterAnimalRequest.getEndDate() != null) {
      whereClause.and(animal.noticeEdt.between(filterAnimalRequest.getStartDate(),
          filterAnimalRequest.getEndDate()));
    }

    List<Animal> animals = queryFactory
        .selectFrom(animal)
        .where(whereClause)
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    return animals;
  }
}
