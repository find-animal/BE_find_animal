package com.example.animal.controller;

import com.example.animal.config.OpenApiProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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

    @DisplayName("loadAnimals: 공공데이터의 유기동물 정보를 가져온다.")
    @Test
    public void loadAnimals() throws Exception {
        //given
        String upKindCd = "417000";
        String urlStr = "http://apis.data.go.kr/1543061/abandonmentPublicSrvc/kind?up_kind_cd="
                + upKindCd
                + "&serviceKey="
                + openApiProperties.getServiceKey()
                + "&_type=json";
        String url = "/open-api/animals/{upkindCd}";

        //when
        final ResultActions resultActions = mockMvc.perform(get(url,upKindCd));

        //then
        resultActions
                .andExpect(status().isOk())
                .andDo(print());
    }

}