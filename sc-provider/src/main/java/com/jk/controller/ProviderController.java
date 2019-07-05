package com.jk.controller;

import com.jk.model.News;
import com.jk.service.CloudService;
import com.jk.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class ProviderController implements CloudService {
    @Autowired
    private ProviderService providerService;

    @Override
    @GetMapping("findnews")
    public HashMap<String, Object> findnews(@RequestParam(value = "start")Integer start,
                                            @RequestParam(value = "pageSize")Integer pageSize) {
        Integer total=providerService.querynewstotal();
        List<News> rows=providerService.querynewsrows(start,pageSize);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("rows",rows);
        System.out.println(map);
        return map;
    }

    @Override
    @GetMapping("test/{hello}")
    public String test(@PathVariable(value = "hello")String hello) {
        System.out.println(hello);
        return hello;
    }

    @Override
    @GetMapping("deletenews")
    public boolean deletenews(@RequestParam(value = "id") Integer id) {
        return providerService.deletenews(id);
    }

    @Override
    @PostMapping("addnews")
    public boolean addnews(@RequestBody News news) {
        news.setReleaseDate(new Date().toLocaleString());
        return providerService.addnews(news);
    }

    @Override
    @GetMapping("selectnewsbyid")
    public News selectnewsbyid(@RequestParam(value = "id") Integer id) {
        return providerService.selectnewsbyid(id);
    }

    @Override
    @PostMapping("updatenews")
    public boolean updatenews(@RequestBody News news) {


        return providerService.updatenews(news);
    }
}
