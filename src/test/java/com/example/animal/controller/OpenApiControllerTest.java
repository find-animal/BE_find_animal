package com.example.animal.controller;

import com.example.animal.domain.cityprovince.entity.CityProvince;
import com.example.animal.domain.district.entity.District;
import com.example.animal.domain.shelter.entity.Shelter;
import com.example.animal.repository.BreedRepository;
import com.example.animal.domain.cityprovince.repository.CityProvinceRepository;
import com.example.animal.domain.district.repository.DistrictRepository;
import com.example.animal.domain.shelter.repository.ShelterRepository;
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
    private WebApplicationContext webApplicationContext;

    @Autowired
    private BreedRepository breedRepository;

    @Autowired
    private ShelterRepository shelterRepository;

    @Autowired
    private CityProvinceRepository cityProvinceRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
//        breedRepository.deleteAll();
//        shelterRepository.deleteAll();
//        cityProvinceRepository.deleteAll();
//        districtRepository.deleteAll();
    }

    @DisplayName("loadSaveDistrict: 공공데이터의 시군구 정보를 가져온뒤 저장한다.")
    @Test
    public void loadSaveDistrict() throws Exception {
        //given
        String url = "/open-api/district";
        String uprCd = "6110000";

        //when
        final ResultActions resultActions = mockMvc.perform(get(url)
                .param("upr_cd",uprCd));

        //then
        resultActions.andExpect(status().isOk());

        List<District> districts = districtRepository.findAll();

        assertThat(districts.size()).isEqualTo(27);
        assertThat(districts.get(0).getOrgCd()).isEqualTo("6119999");
        assertThat(districts.get(0).getOrgdownNm()).isEqualTo("가정보호");
        assertThat(districts.get(0).getCityProvince().getOrgCd()).isEqualTo(uprCd);
    }

    @DisplayName("loadSaveCityProvince: 공공데이터의 시도 정보를 가져온뒤 저장한다.")
    @Test
    public void loadSaveCityProvince() throws Exception {
        //given
        String url = "/open-api/city-province";

        //when
        final ResultActions resultActions = mockMvc.perform(get(url));

        //then
        resultActions.andExpect(status().isOk());

        List<CityProvince> citiesProvinces = cityProvinceRepository.findAll();

        assertThat(citiesProvinces.size()).isEqualTo(17);
        assertThat(citiesProvinces.get(0).getOrgCd()).isEqualTo("6110000");
        assertThat(citiesProvinces.get(0).getOrgdownNm()).isEqualTo("서울특별시");
    }

    @DisplayName("loadSaveBreeds: 공공데이터의 품종 정보를 가져온뒤 저장한다.")
    @Test
    public void loadSaveBreeds() throws Exception {
        //given
        String upKindCd = "429900"; //기타축종 무조건 하나밖에 없음
        String url = "/open-api/breed/{upkindCd}";

        //when
        final ResultActions resultActions = mockMvc.perform(get(url, upKindCd));

        //then
        resultActions.andExpect(status().isOk());

        List<Breed> breeds = breedRepository.findAll();

        assertThat(breeds.size()).isEqualTo(1);
        assertThat(breeds.get(0).getKindCd()).isEqualTo("000117");
        assertThat(breeds.get(0).getKnm()).isEqualTo("기타축종");
    }

    @DisplayName("loadSaveShelter: 공공데이터의 보호소 정보를 가져온뒤 저장한다.")
    @Test
    public void loadSaveShelter() throws Exception {
        //given
        String uprCd = "6110000";
        String orgCd = "3220000";
        String url = "/open-api/shelter";

        //when
        final ResultActions resultActions = mockMvc.perform(get(url)
                .param("uprCd", uprCd)
                .param("orgCd", orgCd));

        //then
        resultActions.andExpect(status().isOk());

        List<Shelter> shelters = shelterRepository.findAll();

        assertThat(shelters.size()).isEqualTo(5);
        assertThat(shelters.get(0).getCareNm()).isEqualTo("한국동물구조관리협회");
        assertThat(shelters.get(0).getCareRegNo()).isEqualTo("311322200900001");
    }

}