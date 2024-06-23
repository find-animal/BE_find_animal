package com.example.animal.domain.animal.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAnimal is a Querydsl query type for Animal
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAnimal extends EntityPathBase<Animal> {

    private static final long serialVersionUID = 1707227550L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAnimal animal = new QAnimal("animal");

    public final com.example.animal.domain.QBaseEntity _super = new com.example.animal.domain.QBaseEntity(this);

    public final StringPath age = createString("age");

    public final StringPath animalBreed = createString("animalBreed");

    public final StringPath colorCd = createString("colorCd");

    public final StringPath fileName = createString("fileName");

    public final DatePath<java.time.LocalDate> happenDt = createDate("happenDt", java.time.LocalDate.class);

    public final StringPath happenPlace = createString("happenPlace");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath kindCd = createString("kindCd");

    public final EnumPath<com.example.animal.domain.enums.NeuterType> neuterYn = createEnum("neuterYn", com.example.animal.domain.enums.NeuterType.class);

    public final DatePath<java.time.LocalDate> noticeEdt = createDate("noticeEdt", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> noticeSdt = createDate("noticeSdt", java.time.LocalDate.class);

    public final StringPath popFile = createString("popFile");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> register_dtm = _super.register_dtm;

    public final EnumPath<com.example.animal.domain.enums.SexType> sexCd = createEnum("sexCd", com.example.animal.domain.enums.SexType.class);

    public final com.example.animal.domain.shelter.entity.QShelter shelter;

    public final StringPath specialMark = createString("specialMark");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> update_dtm = _super.update_dtm;

    public final StringPath weight = createString("weight");

    public QAnimal(String variable) {
        this(Animal.class, forVariable(variable), INITS);
    }

    public QAnimal(Path<? extends Animal> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAnimal(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAnimal(PathMetadata metadata, PathInits inits) {
        this(Animal.class, metadata, inits);
    }

    public QAnimal(Class<? extends Animal> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.shelter = inits.isInitialized("shelter") ? new com.example.animal.domain.shelter.entity.QShelter(forProperty("shelter"), inits.get("shelter")) : null;
    }

}

