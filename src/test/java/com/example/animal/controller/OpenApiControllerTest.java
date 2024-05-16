package com.example.animal.controller;

import com.example.animal.config.OpenApiProperties;
import com.example.animal.domain.Breed;
import com.example.animal.repository.BreedRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OpenApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    private OpenApiProperties openApiProperties;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private BreedRepository breedRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
        breedRepository.deleteAll();
    }

    @DisplayName("loadBreeds: 공공데이터의 품종 정보를 가져온뒤 저장한다.")
    @Test
    public void loadSaveBreeds() throws Exception {
        //given
        String upKindCd = "429900"; //기타축종 무조건 하나밖에 없음
        String url = "/open-api/animals/{upkindCd}";

        //when
        final ResultActions resultActions = mockMvc.perform(get(url, upKindCd));

        //then
        resultActions.andExpect(status().isOk());

        List<Breed> breeds = breedRepository.findAll();

        assertThat(breeds.size()).isEqualTo(1);
        assertThat(breeds.get(0).getKindCd()).isEqualTo("000117");
        assertThat(breeds.get(0).getKnm()).isEqualTo("기타축종");
    }

}