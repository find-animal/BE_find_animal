package com.example.animal.domain.cllient.service;

import com.example.animal.domain.cllient.NaverClient;
import com.example.animal.domain.news.dto.response.ContentResponse;
import com.example.animal.domain.news.dto.response.NewsResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NaverService {

  private final NaverClient client;

  public NewsResponse loadNews(Integer start) {
    NewsResponse news = client.loadNews(start);

    List<ContentResponse> contents = news.getItems().stream().map((item) -> {
      item.setImage(item.getLink());
      return item;
    }).toList();

    news.setItems(contents);

    return news;
  }

}
