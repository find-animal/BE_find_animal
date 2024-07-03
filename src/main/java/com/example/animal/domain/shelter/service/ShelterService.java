package com.example.animal.domain.shelter.service;

import com.example.animal.domain.district.entity.District;
import com.example.animal.domain.district.repository.DistrictRepository;
import com.example.animal.domain.shelter.dto.request.ShelterSearchCondition;
import com.example.animal.domain.shelter.dto.response.ShelterListOpenApiResponse;
import com.example.animal.domain.shelter.dto.response.ShelterPageResponse;
import com.example.animal.domain.shelter.dto.response.ShelterResponse;
import com.example.animal.domain.shelter.dto.response.SheltersResponse;
import com.example.animal.domain.shelter.entity.Shelter;
import com.example.animal.domain.shelter.repository.ShelterRepository;
import com.example.animal.domain.user.entity.User;
import com.example.animal.domain.user.repository.UserRepository;
import com.example.animal.exception.RestApiException;
import com.example.animal.exception.common.CommonErrorCode;
import com.example.animal.exception.user.UserErrorCode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ShelterService {

  private final ShelterRepository shelterRepository;
  private final DistrictRepository districtRepository;
  private final UserRepository userRepository;

  //보호소 상세 정보 조회
  public ShelterResponse getShelterDetail(Long id) {
    Shelter shelter = shelterRepository.findById(id)
        .orElseThrow(() -> new RestApiException(CommonErrorCode.NO_MATCHING_RESOURCE));

    return ShelterResponse.fromEntity(shelter);
  }

  //관심보호소 조회
  @Transactional
  public List<SheltersResponse> findFavoriteShelters(Long userId) {
    //user 조회
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_FOUND_USER));

    //관심보호소 아이디 리스트 조회
    List<Long> favoriteIds = parseFavoriteShelter(user.getFavoriteShelter());

    List<Shelter> shelters = new ArrayList<>();

    for (Long id : favoriteIds) {
      shelters.add(shelterRepository.findById(id)
          .orElseThrow(() -> new RestApiException(CommonErrorCode.NO_MATCHING_RESOURCE)));
    }

    return shelters.stream().map(SheltersResponse::fromEntity).toList();

  }

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

  public ShelterPageResponse getFilteredShelterList(ShelterSearchCondition shelterSearchCondition) {
    int page = shelterSearchCondition.pageNo(); // PatientSearchCondition에 페이지 번호가 있다고 가정
    int size = 20; // 한 페이지에 보여줄 개수
    Pageable pageable = PageRequest.of(page, size);
    Page<Shelter> shelterPage = shelterRepository.findShelterByFilter(shelterSearchCondition,
        pageable);

    List<SheltersResponse> shelters = shelterPage.getContent().stream()
        .map((SheltersResponse::fromEntity))
        .toList();

    return ShelterPageResponse.of(shelters, shelterPage);
  }

  private List<Long> parseFavoriteShelter(String favoriteShelter) {
    return Arrays.stream(favoriteShelter.split(","))
        .filter(s -> !s.isEmpty())
        .map(Long::valueOf)
        .toList();
  }
}
