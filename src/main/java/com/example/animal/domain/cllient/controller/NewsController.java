package com.example.animal.domain.cllient.controller;

import com.example.animal.domain.cllient.service.NaverService;
import com.example.animal.domain.news.dto.response.NewsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "네이버 뉴스 api", description = "유기동물 관련 네이버뉴스 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("${server.api.prefix}/news")
public class NewsController {

  private final NaverService naverService;

  @Operation(summary = "유기동물 관련 네이버 뉴스 조회")
  @GetMapping("/animal/{start}")
  public ResponseEntity<NewsResponse> loadNews(@PathVariable(value = "start") Integer start) {
    NewsResponse news = naverService.loadNews(start);

    return ResponseEntity.ok()
        .body(news);
  }


}
