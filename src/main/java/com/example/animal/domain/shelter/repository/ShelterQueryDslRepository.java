package com.example.animal.domain.shelter.repository;

import com.example.animal.domain.shelter.dto.ShelterSearchCondition;
import com.example.animal.domain.shelter.dto.response.SheltersResponse;
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
        List<SheltersResponse> shelters = query
                .select(Projections.fields(
                        SheltersResponse.class,
                        shelter.id.as("SHELTER_ID"),
                        shelter.careNm.as("care_nm"),
                        shelter.careRegNo.as("care_reg_no"),
                        shelter.careAddr.as("care_addr"),
                        shelter.careTel.as("care_tel")

                ))
                .from(shelter)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 전체 항목 수를 계산합니다.
        long totalCount = query
                .select(shelter.id.countDistinct())
                .from(shelter)
                .fetchCount();
        return new PageImpl<>(shelters, pageable, totalCount);
    }

}
