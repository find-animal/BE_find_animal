package com.example.animal.domain.cllient.service;

import com.example.animal.domain.cllient.NaverClient;
import com.example.animal.domain.news.dto.response.NewsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NaverService {

  private final NaverClient client;

  public NewsResponse loadNews(Integer start) {
    NewsResponse news = client.loadNews(start);

    System.out.println(news);
    return news;
  }

}
