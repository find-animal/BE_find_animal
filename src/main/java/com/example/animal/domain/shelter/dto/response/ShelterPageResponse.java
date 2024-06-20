package com.example.animal.domain.shelter.dto.response;

import com.example.animal.domain.shelter.entity.Shelter;
import java.util.List;
import lombok.Builder;
import org.springframework.data.domain.Page;

@Builder
public record ShelterPageResponse(
    List<SheltersResponse> content,
    int totalPage,
    int pageNumber,
    int pageSize
) {
  public static ShelterPageResponse of(List<SheltersResponse> shelters, Page<Shelter> page) {
    return ShelterPageResponse.builder()
        .content(shelters)
        .totalPage(page.getTotalPages())
        .pageNumber(page.getNumber())
        .pageSize(page.getSize())
        .build();
  }
}
