package com.example.animal.domain.shelter.service;

import com.example.animal.domain.district.entity.District;
import com.example.animal.domain.district.repository.DistrictRepository;
import com.example.animal.domain.shelter.dto.request.ShelterSearchCondition;
import com.example.animal.domain.shelter.dto.response.ShelterListOpenApiResponse;
import com.example.animal.domain.shelter.dto.response.ShelterPageResponse;
import com.example.animal.domain.shelter.dto.response.SheltersResponse;
import com.example.animal.domain.shelter.entity.Shelter;
import com.example.animal.domain.shelter.repository.ShelterRepository;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ShelterService {

  private final ShelterRepository shelterRepository;
  private final DistrictRepository districtRepository;

  //보호소 전체 정보 저장
  public List<Shelter> saveAll(ShelterListOpenApiResponse response, String orgCd) {
    District district = districtRepository.findByOrgCd(orgCd)
        .orElseThrow(() -> new IllegalArgumentException("Not Found cityProvince"));

    return shelterRepository.saveAll(response.getShelters().stream()
        .map((shelter) -> shelter.toEntity(district, district.getCityProvince()))
        .toList());
  }

  public List<Shelter> findByDistrictId(Long id) {
    return shelterRepository.findByDistrictId(id).orElse(Collections.emptyList());
  }

  public List<Shelter> findByCityProvinceId(Long id) {
    return shelterRepository.findByCityProvinceId(id).orElse(Collections.emptyList());
  }

  public ShelterPageResponse getFilteredShelterList(ShelterSearchCondition shelterSearchCondition) {
    int page = shelterSearchCondition.pageNo(); // PatientSearchCondition에 페이지 번호가 있다고 가정
    int size = 20; // 한 페이지에 보여줄 개수
    Pageable pageable = PageRequest.of(page, size,
        Sort.by("seq").ascending()); // 페이지 요청 생성, 이름 기준으로 정렬 예시
    Page<Shelter> shelterPage = shelterRepository.findShelterByFilter(shelterSearchCondition,
        pageable);

    List<SheltersResponse> shelters = shelterPage.getContent().stream()
        .map((SheltersResponse::fromEntity))
        .toList();

    return ShelterPageResponse.of(shelters, shelterPage);
  }
}
