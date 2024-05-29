package com.example.animal.domain.animal.repository;

import com.example.animal.domain.animal.dto.request.FilterAnimalRequest;
import com.example.animal.domain.animal.entity.Animal;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnimalRepositoryCustom {

  Page<Animal> findAnimalByFilter(FilterAnimalRequest filterAnimalRequest,
      Pageable pageable);

}
