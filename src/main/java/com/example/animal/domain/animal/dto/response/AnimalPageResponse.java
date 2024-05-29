package com.example.animal.domain.animal.dto.response;


import com.example.animal.domain.animal.entity.Animal;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@Builder
public class AnimalPageResponse {

  private List<AnimalListResponse> content;
  private int totalPage;
  private int pageNumber;
  private int pageSize;

  public static AnimalPageResponse of(List<AnimalListResponse> animals, Page<Animal> page) {
    return AnimalPageResponse.builder()
        .content(animals)
        .totalPage(page.getTotalPages())
        .pageNumber(page.getNumber())
        .pageSize(page.getSize())
        .build();
  }

}
