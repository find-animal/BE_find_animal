package com.example.animal.domain.district.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDistrict is a Querydsl query type for District
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDistrict extends EntityPathBase<District> {

    private static final long serialVersionUID = 61575682L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDistrict district = new QDistrict("district");

    public final com.example.animal.domain.cityprovince.entity.QCityProvince cityProvince;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath orgCd = createString("orgCd");

    public final StringPath orgdownNm = createString("orgdownNm");

    public final ListPath<com.example.animal.domain.shelter.entity.Shelter, com.example.animal.domain.shelter.entity.QShelter> shelters = this.<com.example.animal.domain.shelter.entity.Shelter, com.example.animal.domain.shelter.entity.QShelter>createList("shelters", com.example.animal.domain.shelter.entity.Shelter.class, com.example.animal.domain.shelter.entity.QShelter.class, PathInits.DIRECT2);

    public QDistrict(String variable) {
        this(District.class, forVariable(variable), INITS);
    }

    public QDistrict(Path<? extends District> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDistrict(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDistrict(PathMetadata metadata, PathInits inits) {
        this(District.class, metadata, inits);
    }

    public QDistrict(Class<? extends District> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cityProvince = inits.isInitialized("cityProvince") ? new com.example.animal.domain.cityprovince.entity.QCityProvince(forProperty("cityProvince")) : null;
    }

}

