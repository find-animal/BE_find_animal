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

}