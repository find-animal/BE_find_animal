package com.example.animal.domain.news.dto.response;

import com.example.animal.util.CustomDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class NewsResponse {
  @JsonProperty("lastBuildDate")
  @JsonDeserialize(using = CustomDateDeserializer.class)
  @JsonFormat(pattern = "E, dd MMM yyyy HH:mm:ss Z")
  private String lastBuildDate;

  @JsonProperty("total")
  private Long total;

  @JsonProperty("start")
  private Integer start;

  @JsonProperty("display")
  private Integer display;

  @JsonProperty("items")
  private List<ContentResponse> items;
}
