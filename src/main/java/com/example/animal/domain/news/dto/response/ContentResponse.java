package com.example.animal.domain.news.dto.response;

import com.example.animal.util.CustomDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.Getter;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentResponse {

  //뉴스 제목
  @JsonProperty("title")
  private String title;

  //원본링크
  @JsonProperty("originallink")
  private String originalLink;

  //뉴스 링크
  @JsonProperty("link")
  private String link;

  //요약된 설명
  @JsonProperty("description")
  private String description;

  //글이 작성된 시간
  @JsonProperty("pubDate")
  @JsonDeserialize(using = CustomDateDeserializer.class)
  @JsonFormat(pattern = "E, dd MMM yyyy HH:mm:ss Z")
  private String pubDate;

  private String image;

  public void setImage(String link) {
    try {
      // HTTP GET 요청 보내기
      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder()
          .uri(new URI(link))
          .header("Content-Type", "text/html")
          .build();

      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

      // HTML 파싱하기
      Document doc = Jsoup.parse(response.body());

      // Open Graph 이미지 메타 태그 추출하기
      Elements ogImageElement = doc.select("meta[property=og:image]");
      String ogImage =
          ogImageElement != null ? ogImageElement.attr("content") : "default_image_url";

      // 이미지 설정
      this.image = ogImage;

    } catch (Exception e) {
      this.image = "default_image_url";
    }
  }

}
