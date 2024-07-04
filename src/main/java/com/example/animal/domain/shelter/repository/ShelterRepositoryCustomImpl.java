package com.example.animal.domain.shelter.repository;

import static com.example.animal.domain.shelter.entity.QShelter.shelter;

import com.example.animal.domain.shelter.dto.request.ShelterSearchCondition;
import com.example.animal.domain.shelter.entity.Shelter;
import com.example.animal.exception.RestApiException;
import com.example.animal.exception.common.CommonErrorCode;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.Expressions;
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
public class ShelterRepositoryCustomImpl implements ShelterRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  @Override
  public Page<Shelter> findShelterByFilter(ShelterSearchCondition shelterSearchCondition,
      Pageable pageable) {

    BooleanBuilder whereClause = new BooleanBuilder();

    if (shelterSearchCondition.cityProvinceIds() != null) {
      whereClause.and(shelter.cityProvince.id.in(shelterSearchCondition.cityProvinceIds()));
    }

    List<Shelter> content = getContent(pageable, whereClause);

    JPQLQuery<Shelter> count = getCount(whereClause);

    return PageableExecutionUtils.getPage(content, pageable, count::fetchCount);
  }

  private List<Shelter> getContent(Pageable pageable, BooleanBuilder whereClause) {
    List<Shelter> content = queryFactory
        .selectFrom(shelter)
        .where(whereClause)
        .orderBy(
            Expressions.stringTemplate(
                "case when {0} is null then 1 else 0 end", shelter.careAddr
            ).asc()
        )
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    if (content.isEmpty()) {
      throw new RestApiException(CommonErrorCode.NO_MATCHING_RESOURCE);
    }

    return content;
  }

  private JPQLQuery<Shelter> getCount(BooleanBuilder whereClause) {
    return queryFactory
        .selectFrom(shelter)
        .where(whereClause);
  }
}
