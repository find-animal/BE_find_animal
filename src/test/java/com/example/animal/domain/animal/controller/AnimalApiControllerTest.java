package com.example.animal.domain.animal.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.animal.domain.animal.entity.Animal;
import com.example.animal.domain.animal.repository.AnimalRepository;
import com.example.animal.domain.enums.NeuterType;
import com.example.animal.domain.enums.SexType;
import com.example.animal.domain.shelter.entity.Shelter;
import com.example.animal.domain.shelter.repository.ShelterRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
class AnimalApiControllerTest {

  @Autowired
  protected MockMvc mockMvc;
  @Autowired
  AnimalRepository animalRepository;

  @Autowired
  ShelterRepository shelterRepository;

  @DisplayName("findAnimal: 유기동물 정보 조회에 성공한다.")
  @Test
  public void findAnimal() throws Exception {
    //given
    final String url = "/api/animals/{id}";
    final String careNm = "careNm";

    Shelter savedShelter = shelterRepository.save(Shelter.builder()
        .careNm(careNm)
        .build());

    final String popFile = "popFile";
    final String specialMark = "specialMark";
    final String age = "age";
    final String colorCd = "colorCd";
    final NeuterType neuterYn = NeuterType.Y;
    final SexType sexCd = SexType.M;
    final String weight = "weight";
    final String happenPlace = "happenPlace";
    final String animalBreed = "animalBreed";

    Animal savedAnimal = animalRepository.save(Animal.builder()
        .popFile(popFile)
        .specialMark(specialMark)
        .age(age)
        .colorCd(colorCd)
        .neuterYn(neuterYn)
        .sexCd(sexCd)
        .weight(weight)
        .happenPlace(happenPlace)
        .kindCd("[개] animalBreed")
        .shelter(savedShelter)
        .build());

    //when
    final ResultActions resultActions = mockMvc.perform(get(url, savedAnimal.getId()));

    //then
    resultActions
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.popFile").value(popFile))
        .andExpect(jsonPath("$.specialMark").value(specialMark))
        .andExpect(jsonPath("$.age").value(age))
        .andExpect(jsonPath("$.colorCd").value(colorCd))
        .andExpect(jsonPath("$.neuterYn").value(neuterYn.toString()))
        .andExpect(jsonPath("$.sexCd").value(sexCd.toString()))
        .andExpect(jsonPath("$.weight").value(weight))
        .andExpect(jsonPath("$.happenPlace").value(happenPlace))
        .andExpect(jsonPath("$.animalBreed").value(animalBreed))
        .andExpect(jsonPath("$.shelter.careNm").value(careNm));
  }

  @DisplayName("findAnimal: 필터조건에 맞는 유기 동물 리스트 조회에 성공한다.")
  @Test
  public void findAnimals() throws Exception {
    //given
    final String url = "/api/animals";
    final String startYear = "2026";
    final String endYear = "2027";
    final SexType sexCd = SexType.M;
    final LocalDate startDate = LocalDate.of(2026, 5, 23);
    final LocalDate endDate = LocalDate.of(2026, 5, 24);
    final Long districtId = 238L;
    final int page = 0;
    final int size = 1;

    final String fileName = "fileName";
    final String animalBreed = "animalBreed";
    final String happenPlace = "happenPlace";
    final LocalDate happenDt = LocalDate.of(2026, 5, 20);

    final Shelter shelter = shelterRepository.findById(1263L)
        .orElseThrow(() -> new IllegalArgumentException("Not Found Shelter"));

    Animal savedAnimal = animalRepository.save(Animal.builder()
        .age(startYear)
        .sexCd(sexCd)
        .fileName(fileName)
        .kindCd("[개] animalBreed")
        .happenPlace(happenPlace)
        .happenDt(happenDt)
        .age(startYear)
        .noticeEdt(startDate)
        .shelter(shelter)
        .build());

    //when
    final ResultActions resultActions = mockMvc.perform(get(url)
        .param("startYear", startYear)
        .param("endYear", endYear)
        .param("sexCd", sexCd.toString())
        .param("startDate", startDate.toString())
        .param("endDate", endDate.toString())
        .param("districtId", districtId.toString())
        .param("page", String.valueOf(page))
        .param("size", String.valueOf(size))
    );

    //then
    resultActions
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(size))
        .andExpect(jsonPath("$[0].id").value(savedAnimal.getId()))
        .andExpect(jsonPath("$[0].fileName").value(fileName))
        .andExpect(jsonPath("$[0].animalBreed").value(animalBreed))
        .andExpect(jsonPath("$[0].happenPlace").value(happenPlace))
        .andExpect(jsonPath("$[0].happenDt").value(happenDt.toString()))
        .andExpect(jsonPath("$[0].age").value(startYear))
        .andExpect(jsonPath("$[0].sexCd").value(sexCd.toString()))
        .andExpect(jsonPath("$[0].noticeEdt").value(String.valueOf(startDate)));

  }

}