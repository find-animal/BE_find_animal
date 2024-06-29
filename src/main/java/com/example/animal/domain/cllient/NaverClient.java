package com.example.animal.domain.cllient;

import com.example.animal.domain.news.dto.response.NewsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "naver")
public interface NaverClient {

  @GetMapping("")
  NewsResponse loadNews(
      @RequestParam(value = "start") Integer start
  );


}
