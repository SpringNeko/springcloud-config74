package com.jk.service;

import com.jk.mapper.ProviderMapper;
import com.jk.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProviderServiceImpl implements ProviderService {
    @Autowired
    private ProviderMapper providerMapper;

    @Override
    public Integer querynewstotal() {
        return providerMapper.querynewstotal();
    }

    @Override
    public List<News> querynewsrows(Integer start, Integer pageSize) {
        return providerMapper.querynewsrows(start,pageSize);
    }

    @Override
    public boolean deletenews(Integer id) {
        return providerMapper.deletenews(id);
    }

    @Override
    public boolean addnews(News news) {
        return providerMapper.addnews(news);
    }

    @Override
    public News selectnewsbyid(Integer id) {
        return providerMapper.selectnewsbyid(id);
    }

    @Override
    public boolean updatenews(News news) {
        return providerMapper.updatenews(news);
    }
}
