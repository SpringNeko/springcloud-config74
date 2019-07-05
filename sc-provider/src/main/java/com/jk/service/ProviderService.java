package com.jk.service;

import com.jk.model.News;

import java.util.List;

public interface ProviderService {
    Integer querynewstotal();

    List<News> querynewsrows(Integer start, Integer pageSize);

    boolean deletenews(Integer id);

    boolean addnews(News news);

    News selectnewsbyid(Integer id);

    boolean updatenews(News news);
}
