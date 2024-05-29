package com.example.animal.domain.animal.repository;

import static com.example.animal.domain.animal.entity.QAnimal.animal;

import com.example.animal.domain.animal.dto.request.FilterAnimalRequest;
import com.example.animal.domain.animal.entity.Animal;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AnimalRepositoryCustomImpl implements AnimalRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  @Override
  public Page<Animal> findAnimalByFilter(FilterAnimalRequest filterAnimalRequest,
      Pageable pageable) {

    BooleanBuilder whereClause = new BooleanBuilder();

    setWhereClause(filterAnimalRequest, whereClause);

    List<Animal> content = getContent(pageable, whereClause);

    JPQLQuery<Animal> count = getCount(whereClause);

    return PageableExecutionUtils.getPage(content, pageable, count::fetchCount);
  }

  private static void setWhereClause(FilterAnimalRequest filterAnimalRequest,
      BooleanBuilder whereClause) {
    checkSex(filterAnimalRequest, whereClause);
    checkDistrict(filterAnimalRequest, whereClause);
    checkCityProvince(filterAnimalRequest, whereClause);
    checkAge(filterAnimalRequest, whereClause);
    checkNoticeDate(filterAnimalRequest, whereClause);
  }

  private static void checkNoticeDate(FilterAnimalRequest filterAnimalRequest,
      BooleanBuilder whereClause) {
    if (filterAnimalRequest.getStartDate() != null && filterAnimalRequest.getEndDate() != null) {
      whereClause.and(animal.noticeEdt.between(filterAnimalRequest.getStartDate(),
          filterAnimalRequest.getEndDate()));
    }
  }

  private static void checkAge(FilterAnimalRequest filterAnimalRequest,
      BooleanBuilder whereClause) {
    if (filterAnimalRequest.getStartYear() != null && filterAnimalRequest.getEndYear() != null) {
      whereClause.and(
          animal.age.between(filterAnimalRequest.getStartYear(), filterAnimalRequest.getEndYear()));
    }
  }

  private static void checkCityProvince(FilterAnimalRequest filterAnimalRequest,
      BooleanBuilder whereClause) {
    if (filterAnimalRequest.getCityProvinceId() != null) {
      whereClause.and(animal.shelter.cityProvince.id.eq(filterAnimalRequest.getCityProvinceId()));
    }
  }

  private static void checkDistrict(FilterAnimalRequest filterAnimalRequest,
      BooleanBuilder whereClause) {
    if (filterAnimalRequest.getDistrictId() != null) {
      whereClause.and(animal.shelter.district.id.eq(filterAnimalRequest.getDistrictId()));
    }
  }

  private static void checkSex(FilterAnimalRequest filterAnimalRequest,
      BooleanBuilder whereClause) {
    if (filterAnimalRequest.getSexCd() != null) {
      whereClause.and(animal.sexCd.eq(filterAnimalRequest.getSexCd()));
    }
  }

  private List<Animal> getContent(Pageable pageable, BooleanBuilder whereClause) {
    return queryFactory
        .selectFrom(animal)
        .where(whereClause)
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();
  }

  private JPQLQuery<Animal> getCount(BooleanBuilder whereClause) {
    return queryFactory
        .selectFrom(animal)
        .where(whereClause);
  }

}