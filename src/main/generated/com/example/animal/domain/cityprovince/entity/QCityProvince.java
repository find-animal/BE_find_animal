package com.example.animal.domain.cityprovince.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCityProvince is a Querydsl query type for CityProvince
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCityProvince extends EntityPathBase<CityProvince> {

    private static final long serialVersionUID = 1075483452L;

    public static final QCityProvince cityProvince = new QCityProvince("cityProvince");

    public final com.example.animal.domain.QBaseEntity _super = new com.example.animal.domain.QBaseEntity(this);

    public final ListPath<com.example.animal.domain.district.entity.District, com.example.animal.domain.district.entity.QDistrict> districts = this.<com.example.animal.domain.district.entity.District, com.example.animal.domain.district.entity.QDistrict>createList("districts", com.example.animal.domain.district.entity.District.class, com.example.animal.domain.district.entity.QDistrict.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath orgCd = createString("orgCd");

    public final StringPath orgdownNm = createString("orgdownNm");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> register_dtm = _super.register_dtm;

    public final ListPath<com.example.animal.domain.shelter.entity.Shelter, com.example.animal.domain.shelter.entity.QShelter> shelters = this.<com.example.animal.domain.shelter.entity.Shelter, com.example.animal.domain.shelter.entity.QShelter>createList("shelters", com.example.animal.domain.shelter.entity.Shelter.class, com.example.animal.domain.shelter.entity.QShelter.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> update_dtm = _super.update_dtm;

    public QCityProvince(String variable) {
        super(CityProvince.class, forVariable(variable));
    }

    public QCityProvince(Path<? extends CityProvince> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCityProvince(PathMetadata metadata) {
        super(CityProvince.class, metadata);
    }

}

