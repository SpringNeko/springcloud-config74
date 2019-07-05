package com.jk.service;

import com.jk.model.News;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@RequestMapping("/error")
@Component
public class NewsServiceError implements FeignService  {



    @Override
    public HashMap<String, Object> findnews(Integer start, Integer pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",0);
        map.put("rows",0);
        System.out.println(map);
        return map;
    }

    @Override
    public String test(String hello) {
        return "error"+hello;
    }

    @Override
    public boolean deletenews(Integer id) {
        return false;
    }

    @Override
    public boolean addnews(News news) {
        return false;
    }

    @Override
    public News selectnewsbyid(Integer id) {
        return null;
    }

    @Override
    public boolean updatenews(News news) {
        return false;
    }
}
