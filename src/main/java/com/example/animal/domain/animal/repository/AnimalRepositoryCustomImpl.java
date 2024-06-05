package com.example.animal.domain.animal.repository;

import static com.example.animal.domain.animal.entity.QAnimal.animal;

import com.example.animal.domain.animal.dto.request.AnimalSearchCondition;
import com.example.animal.domain.animal.entity.Animal;
import com.example.animal.exception.RestApiException;
import com.example.animal.exception.animal.validator.AgeRangeValidator;
import com.example.animal.exception.common.CommonErrorCode;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
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
  public Page<Animal> findAnimalByFilter(AnimalSearchCondition animalSearchCondition,
      Pageable pageable) {

    BooleanBuilder whereClause = new BooleanBuilder();

    setWhereClause(animalSearchCondition, whereClause);

    List<Animal> content = getContent(pageable, whereClause);

    JPQLQuery<Animal> count = getCount(whereClause);

    return PageableExecutionUtils.getPage(content, pageable, count::fetchCount);
  }

  private static void setWhereClause(AnimalSearchCondition animalSearchCondition,
      BooleanBuilder whereClause) {
    checkSex(animalSearchCondition, whereClause);
    checkCityProvince(animalSearchCondition, whereClause);
    setAgeRange(animalSearchCondition, whereClause);
    checkNoticeDate(animalSearchCondition, whereClause);
  }

  private static void checkNoticeDate(AnimalSearchCondition animalSearchCondition,
      BooleanBuilder whereClause) {
    if (animalSearchCondition.canAdopt()) {
      whereClause.and(animal.noticeEdt.after(LocalDate.now()));
    }
  }

  private static void setAgeRange(AnimalSearchCondition animalSearchCondition,
      BooleanBuilder whereClause) {
    AgeRangeValidator.validate(animalSearchCondition);

    if (animalSearchCondition.startYear() != null) {
      whereClause.and(
          animal.age.between(animalSearchCondition.startYear(), animalSearchCondition.endYear()));
    }
  }

  private static void checkCityProvince(AnimalSearchCondition animalSearchCondition,
      BooleanBuilder whereClause) {
    if (animalSearchCondition.cityProvinceIds() != null) {
      whereClause.and(animal.shelter.cityProvince.id.in(animalSearchCondition.cityProvinceIds()));
    }
  }

  private static void checkSex(AnimalSearchCondition animalSearchCondition,
      BooleanBuilder whereClause) {
    if (animalSearchCondition.sexCd() != null) {
      whereClause.and(animal.sexCd.eq(animalSearchCondition.sexCd()));
    }
  }

  private List<Animal> getContent(Pageable pageable, BooleanBuilder whereClause) {
    List<Animal> content = queryFactory
        .selectFrom(animal)
        .where(whereClause)
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    if(content.isEmpty()) {
      throw new RestApiException(CommonErrorCode.NO_MATCHING_RESOURCE);
    }

    return content;
  }

  private JPQLQuery<Animal> getCount(BooleanBuilder whereClause) {
    return queryFactory
        .selectFrom(animal)
        .where(whereClause);
  }

}
