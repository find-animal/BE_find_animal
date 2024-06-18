package com.example.animal.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -212462212L;

    public static final QUser user = new QUser("user");

    public final com.example.animal.domain.QBaseEntity _super = new com.example.animal.domain.QBaseEntity(this);

    public final StringPath email = createString("email");

    public final StringPath favoriteAnimal = createString("favoriteAnimal");

    public final StringPath id = createString("id");

    public final StringPath password = createString("password");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> register_dtm = _super.register_dtm;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> update_dtm = _super.update_dtm;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

