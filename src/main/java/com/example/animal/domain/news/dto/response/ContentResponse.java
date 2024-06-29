package com.example.animal.domain.news.dto.response;

import com.example.animal.util.CustomDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

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

}
