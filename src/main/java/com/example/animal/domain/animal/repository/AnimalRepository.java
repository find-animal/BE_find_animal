package com.example.animal.domain.animal.repository;

import com.example.animal.domain.animal.entity.Animal;
import com.example.animal.domain.enums.SexType;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

public interface AnimalRepository extends JpaRepository<Animal, Long>,
    QuerydslPredicateExecutor<Animal> {

  @Query("SELECT a FROM Animal a WHERE " +
      "(:startAge IS NULL OR :endAge IS NULL OR a.age BETWEEN :startAge AND :endAge) " +
      "AND (:sexCd IS NULL OR a.sexCd = :sexCd) " +
      "AND (:startDate IS NULL OR :endDate IS NULL OR a.noticeEdt BETWEEN :startDate AND :endDate)"
  )
  Page<Animal> findByFilters(
      @Param("startAge") String startAge,
      @Param("endAge") String endAge,
      @Param("sexCd") SexType sexCd,
      @Param("startDate") LocalDate startDate,
      @Param("endDate") LocalDate endDate,
      Pageable pageable
  );
}
