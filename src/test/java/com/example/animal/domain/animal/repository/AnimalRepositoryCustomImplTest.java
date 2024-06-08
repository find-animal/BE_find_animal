package com.example.animal.domain.animal.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import com.example.animal.config.QuerydslConfig;
import com.example.animal.domain.animal.dto.request.AnimalSearchCondition;
import com.example.animal.domain.animal.entity.Animal;
import com.example.animal.domain.cityprovince.entity.CityProvince;
import com.example.animal.domain.cityprovince.repository.CityProvinceRepository;
import com.example.animal.domain.district.entity.District;
import com.example.animal.domain.district.repository.DistrictRepository;
import com.example.animal.domain.enums.SexType;
import com.example.animal.domain.shelter.entity.Shelter;
import com.example.animal.domain.shelter.repository.ShelterRepository;
import com.example.animal.exception.RestApiException;
import com.example.animal.exception.animal.AnimalErrorCode;
import com.example.animal.exception.common.CommonErrorCode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;


@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@Import(QuerydslConfig.class)
class AnimalRepositoryCustomImplTest {

  @Autowired
  private AnimalRepository animalRepository;

  @Autowired
  private CityProvinceRepository cityProvinceRepository;

  @Autowired
  private DistrictRepository districtRepository;

  @Autowired
  private ShelterRepository shelterRepository;

  private Animal savedAnimal;

  @BeforeEach
  void setUp() {
    CityProvince cityProvince = CityProvince.builder()
        .orgCd("6110000")
        .orgdownNm("서울특별시")
        .build();
    CityProvince savedCityProvince = cityProvinceRepository.save(cityProvince);

    District district = District.builder()
        .orgCd("3220000")
        .orgdownNm("강남구")
        .cityProvince(savedCityProvince)
        .build();
    District savedDistrict = districtRepository.save(district);

    Shelter shelter = Shelter.builder()
        .careNm("careNm")
        .careRegNo("careRegNo")
        .cityProvince(savedCityProvince)
        .district(savedDistrict)
        .build();
    Shelter savedShelter = shelterRepository.save(shelter);

    Animal animal = Animal.builder()
        .age("2024(년생)")
        .colorCd("검/흰")
        .happenDt(LocalDate.of(2024, 5, 29))
        .kindCd("[개] 믹스견")
        .sexCd(SexType.M)
        .noticeEdt(LocalDate.of(2024, 6, 10))
        .noticeSdt(LocalDate.of(2024, 5, 30))
        .shelter(savedShelter)
        .build();
    savedAnimal = animalRepository.save(animal);
  }

  @DisplayName("[성공] 태어난 연도에 맞는 조건의 동물을 검색한다.")
  @Test
  void findAnimalByAge() throws Exception {
    //given
    String startYear = "2024";
    String endYear = "2025";
    Pageable pageable = PageRequest.of(0, 10, Sort.by("noticeSdt").descending());

    AnimalSearchCondition searchCondition = new AnimalSearchCondition(startYear, endYear, null,
        null, null);

    //when
    Page<Animal> result = animalRepository.findAnimalByFilter(searchCondition, pageable);

    //then
    assertThat(result).isNotNull();
    assertThat(result.getTotalElements()).isEqualTo(1);
    assertThat(result.getContent().get(0).getId()).isEqualTo(savedAnimal.getId());
  }

  @DisplayName("[실패] 태어난 연도의 검색 조건의 시작 연도는 끝나는 연도보다 같거나 클수 없다.")
  @Test
  void startYearCannotBeGreaterThanOrEqualToEndYear() {
    //given
    String startYear = "2026";
    String endYear = "2025";
    Pageable pageable = PageRequest.of(0, 10, Sort.by("noticeSdt").descending());

    AnimalSearchCondition searchCondition = new AnimalSearchCondition(startYear, endYear, null,
        null, null);

    //when
    Throwable thrown = catchThrowable(
        () -> animalRepository.findAnimalByFilter(searchCondition, pageable));

    //then
    assertThat(thrown)
        .isInstanceOf(RestApiException.class)
        .hasFieldOrPropertyWithValue("errorCode", AnimalErrorCode.INVALID_AGE_RANGE);
  }

  @DisplayName("[실패] 태어난 연도의 검색 조건의 시작 연도와 끝나는 연도 둘다 있거나 아예 없어야 한다.")
  @Test
  void startYearAndEndYearBothExistOrAbsent() {
    //given
    String startYear = "2024";
    Pageable pageable = PageRequest.of(0, 10, Sort.by("noticeSdt").descending());

    AnimalSearchCondition searchCondition = new AnimalSearchCondition(startYear, null, null, null,
        null);

    //when
    Throwable thrown = catchThrowable(
        () -> animalRepository.findAnimalByFilter(searchCondition, pageable));

    //then
    assertThat(thrown)
        .isInstanceOf(RestApiException.class)
        .hasFieldOrPropertyWithValue("errorCode", AnimalErrorCode.MISSING_REQUIRED_FIELDS);
  }

  @DisplayName("[성공] 성별 조건에 맞는 유기동물을 검색한다.")
  @Test
  void findAnimalBySex() throws Exception {
    //given
    SexType sexCd = SexType.M;
    Pageable pageable = PageRequest.of(0, 10, Sort.by("noticeSdt").descending());

    AnimalSearchCondition searchCondition = new AnimalSearchCondition(null, null, sexCd, null,
        null);

    //when
    Page<Animal> result = animalRepository.findAnimalByFilter(searchCondition, pageable);

    //then
    assertThat(result).isNotNull();
    assertThat(result.getTotalElements()).isEqualTo(1);
    assertThat(result.getContent().get(0).getId()).isEqualTo(savedAnimal.getId());
  }

  @DisplayName("[성공] 현재 입양 가능한 유기동물을 검색한다.")
  @Test
  void findAnimalByCanAdopt() throws Exception {
    //given
    Boolean canAdopt = true;
    Pageable pageable = PageRequest.of(0, 10, Sort.by("noticeSdt").descending());

    AnimalSearchCondition searchCondition = new AnimalSearchCondition(null, null, null, canAdopt,
        null);

    //when
    Page<Animal> result = animalRepository.findAnimalByFilter(searchCondition, pageable);

    //then
    assertThat(result).isNotNull();
    assertThat(result.getTotalElements()).isEqualTo(1);
    assertThat(result.getContent().get(0).getId()).isEqualTo(savedAnimal.getId());
  }

  @DisplayName("[성공] 특정 지역에 보호중인 유기동물을 조회한다.")
  @Test
  void findAnimalByCityProvince() throws Exception {
    //given
    List<Long> cityProvinceIds = new ArrayList<>(
        Arrays.asList(savedAnimal.getShelter().getCityProvince().getId()));
    Pageable pageable = PageRequest.of(0, 10, Sort.by("noticeSdt").descending());

    AnimalSearchCondition searchCondition = new AnimalSearchCondition(null, null, null, null,
        cityProvinceIds);

    //when
    Page<Animal> result = animalRepository.findAnimalByFilter(searchCondition, pageable);

    //then
    assertThat(result).isNotNull();
    assertThat(result.getTotalElements()).isEqualTo(1);
    assertThat(result.getContent().get(0).getId()).isEqualTo(savedAnimal.getId());
  }

  @DisplayName("조건에 맞는 유기동물이 없다.")
  @Test
  void noMatchedAnimal() throws Exception {
    //given
    SexType sexCd = SexType.F;
    Pageable pageable = PageRequest.of(0, 10, Sort.by("noticeSdt").descending());

    AnimalSearchCondition searchCondition = new AnimalSearchCondition(null, null, sexCd, null,
        null);

    //when
    Throwable thrown = catchThrowable(
        () -> animalRepository.findAnimalByFilter(searchCondition, pageable));

    //then
    assertThat(thrown)
        .isInstanceOf(RestApiException.class)
        .hasFieldOrPropertyWithValue("errorCode", CommonErrorCode.NO_MATCHING_RESOURCE);
  }
}