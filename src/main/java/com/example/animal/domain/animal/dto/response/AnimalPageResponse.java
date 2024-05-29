package com.example.animal.domain.animal.dto.response;


import com.example.animal.domain.animal.entity.Animal;
import java.util.List;
import java.util.Objects;
import lombok.Builder;
import org.springframework.data.domain.Page;

@Builder
public record AnimalPageResponse(
    List<AnimalListResponse> content,
    int totalPage,
    int pageNumber,
    int pageSize
) {

  public AnimalPageResponse {
    Objects.requireNonNull(totalPage, "totalPage must not be null");
    Objects.requireNonNull(pageNumber, "pageNumber must not be null");
    Objects.requireNonNull(pageSize, "pageSize must not be null");
  }

  public static AnimalPageResponse of(List<AnimalListResponse> animals, Page<Animal> page) {
    return AnimalPageResponse.builder()
        .content(animals)
        .totalPage(page.getTotalPages())
        .pageNumber(page.getNumber())
        .pageSize(page.getSize())
        .build();
  }

}