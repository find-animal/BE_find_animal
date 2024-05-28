package com.example.animal.domain.animal.dto.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.animal.domain.animal.dto.response.QAnimalListResponse is a Querydsl Projection type for AnimalListResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QAnimalListResponse extends ConstructorExpression<AnimalListResponse> {

    private static final long serialVersionUID = -811427376L;

    public QAnimalListResponse(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> fileName, com.querydsl.core.types.Expression<String> animalBreed, com.querydsl.core.types.Expression<String> happenPlace, com.querydsl.core.types.Expression<java.time.LocalDate> happenDt, com.querydsl.core.types.Expression<String> age, com.querydsl.core.types.Expression<com.example.animal.domain.enums.SexType> sexCd, com.querydsl.core.types.Expression<String> weight, com.querydsl.core.types.Expression<java.time.LocalDate> noticeSdt, com.querydsl.core.types.Expression<java.time.LocalDate> noticeEdt) {
        super(AnimalListResponse.class, new Class<?>[]{long.class, String.class, String.class, String.class, java.time.LocalDate.class, String.class, com.example.animal.domain.enums.SexType.class, String.class, java.time.LocalDate.class, java.time.LocalDate.class}, id, fileName, animalBreed, happenPlace, happenDt, age, sexCd, weight, noticeSdt, noticeEdt);
    }

}

