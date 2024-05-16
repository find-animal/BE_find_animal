package com.example.animal.controller;

import com.example.animal.config.OpenApiProperties;
import com.example.animal.dto.response.BreedsListResponse;
import com.example.animal.dto.response.ShelterListResponse;
import com.example.animal.service.BreedService;
import com.example.animal.service.OpenApiService;
import com.example.animal.service.ShelterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Tag(name = "공공데이터 api", description = "공공데이터를 활용한 데이터 저장용 API")
@RequiredArgsConstructor
@RestController
public class OpenApiController {

    private final OpenApiProperties openApiProperties;
    private final OpenApiService openApiService;
    private final BreedService breedService;
    private final ShelterService shelterService;

    @Operation(summary = "보호소 조회 및 저장", description = "파라미터로 받은 시도, 시군구코드를 통해 보호소를 조회하고 저장합니다.")
    @Parameter(name = "uprCd", description = "시도코드 미입력시 데이터 x")
    @Parameter(name = "orgCd", description = "시군구코드 미입력시 데이터 x")
    @GetMapping("/open-api/shelter")
    public ResponseEntity<ShelterListResponse> loadSaveShelter(@RequestParam(value = "uprCd") String uprCd, @RequestParam(value = "orgCd") String orgCd) {
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        String result = null;

        String urlStr = openApiProperties.getBaseUrl() + "shelter?upr_cd="
                + uprCd
                + "&org_cd="
                + orgCd
                + "&serviceKey="
                + openApiProperties.getServiceKey()
                + "&_type=json";

        try {
            URL url = new URL(urlStr);

            urlConnection = (HttpURLConnection) url.openConnection();
            inputStream = getNetworkConnection(urlConnection);
            result = readStreamToString(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (urlConnection != null) urlConnection.disconnect();
        }

        ShelterListResponse shelters = openApiService.parsingJsonObject(result, ShelterListResponse.class);

        shelterService.saveAll(shelters);

        return ResponseEntity.ok()
                .body(shelters);

    }

    @Operation(summary = "품종 조회 및 저장", description = "파라미터로 받은 품종을 조회하고 저장합니다.")
    @Parameter(name = "upKindCd", description = "개: 417000, 고양이: 422400, 기타: 429900")
    @GetMapping("/open-api/breed/{upKindCd}")
    public ResponseEntity<BreedsListResponse> loadSaveBreads(@PathVariable(name = "upKindCd") String upKindCd) {
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        String result = null;

        String urlStr = openApiProperties.getBaseUrl() + "kind?up_kind_cd="
                + upKindCd
                + "&serviceKey="
                + openApiProperties.getServiceKey()
                + "&_type=json";

        try {
            URL url = new URL(urlStr);

            urlConnection = (HttpURLConnection) url.openConnection();
            inputStream = getNetworkConnection(urlConnection);
            result = readStreamToString(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (urlConnection != null) urlConnection.disconnect();
        }

        BreedsListResponse animals = openApiService.parsingJsonObject(result, BreedsListResponse.class);

        breedService.saveAll(animals);

        return ResponseEntity.ok()
                .body(animals);
    }

    /*
        URLConnection을 전달받아 연결정보를 설정한 후 연결, 연결후 수신한 inputStream을 반환
     */
    private InputStream getNetworkConnection(HttpURLConnection httpURLConnection) throws IOException {
        httpURLConnection.setConnectTimeout(3000);
        httpURLConnection.setReadTimeout(3000);
        httpURLConnection.setRequestMethod("GET");

        if (httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new IOException("HTTP error code: " + httpURLConnection.getResponseCode());
        }

        return httpURLConnection.getInputStream();
    }

    /*
        inputStream을 전달받아 문자열로 변환 후 반환
     */
    private String readStreamToString(InputStream inputStream) throws IOException {
        StringBuilder result = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

        String readLine;
        while ((readLine = br.readLine()) != null) {
            result.append(readLine + "\n\r");
        }

        br.close();

        return result.toString();
    }


}
