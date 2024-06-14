package com.example.animal.domain.shelter.repository;

import com.example.animal.domain.shelter.dto.ShelterSearchCondition;
import com.example.animal.domain.shelter.dto.response.SheltersResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.animal.domain.shelter.entity.QShelter.shelter;

@Repository
public class ShelterQueryDslRepository {
    @Autowired
    EntityManager em;

    JPAQueryFactory query;

    public ShelterQueryDslRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);;
    }

    public Page<SheltersResponse> findAllShelters(ShelterSearchCondition shelterSearchCondition, Pageable pageable) {
        BooleanBuilder whereClause = new BooleanBuilder();
        if (!shelterSearchCondition.cityProvinceId().isEmpty()) {
            whereClause.and(shelter.cityProvince.id.in(shelterSearchCondition.cityProvinceId()));
        }

        List<SheltersResponse> shelters = query
                .select(Projections.constructor(
                        SheltersResponse.class,
                        shelter.id.as("id"),
                        shelter.careNm.as("careNm"),
                        shelter.careRegNo.as("careRegNo"),
                        shelter.careAddr.as("careAddr"),
                        shelter.careTel.as("careTel")
                ))
                .from(shelter)
                .where(whereClause)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 전체 항목 수를 계산합니다.
        long totalCount = query
                .select(shelter.id.countDistinct())
                .from(shelter)
                .where(whereClause)
                .fetchCount();
        return new PageImpl<>(shelters, pageable, totalCount);
    }

}
