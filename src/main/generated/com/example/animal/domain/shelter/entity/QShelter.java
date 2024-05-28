package com.example.animal.domain.shelter.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QShelter is a Querydsl query type for Shelter
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShelter extends EntityPathBase<Shelter> {

    private static final long serialVersionUID = 2146582316L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShelter shelter = new QShelter("shelter");

    public final ListPath<com.example.animal.domain.animal.entity.Animal, com.example.animal.domain.animal.entity.QAnimal> animals = this.<com.example.animal.domain.animal.entity.Animal, com.example.animal.domain.animal.entity.QAnimal>createList("animals", com.example.animal.domain.animal.entity.Animal.class, com.example.animal.domain.animal.entity.QAnimal.class, PathInits.DIRECT2);

    public final StringPath careAddr = createString("careAddr");

    public final StringPath careNm = createString("careNm");

    public final StringPath careRegNo = createString("careRegNo");

    public final StringPath careTel = createString("careTel");

    public final StringPath chargeNm = createString("chargeNm");

    public final com.example.animal.domain.cityprovince.entity.QCityProvince cityProvince;

    public final com.example.animal.domain.district.entity.QDistrict district;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath officeTel = createString("officeTel");

    public final StringPath orgNm = createString("orgNm");

    public QShelter(String variable) {
        this(Shelter.class, forVariable(variable), INITS);
    }

    public QShelter(Path<? extends Shelter> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QShelter(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QShelter(PathMetadata metadata, PathInits inits) {
        this(Shelter.class, metadata, inits);
    }

    public QShelter(Class<? extends Shelter> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cityProvince = inits.isInitialized("cityProvince") ? new com.example.animal.domain.cityprovince.entity.QCityProvince(forProperty("cityProvince")) : null;
        this.district = inits.isInitialized("district") ? new com.example.animal.domain.district.entity.QDistrict(forProperty("district"), inits.get("district")) : null;
    }

}

