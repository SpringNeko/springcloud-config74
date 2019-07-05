package com.jk.service;

import com.jk.model.News;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

public interface CloudService {
    @GetMapping("findnews")
    HashMap<String, Object> findnews(@RequestParam(value = "start")
                                         Integer start,
                                     @RequestParam(value = "pageSize") Integer pageSize);
    @GetMapping("test/{hello}")
    String test(@PathVariable(value = "hello") String hello);

    @GetMapping("deletenews")
    boolean deletenews(@RequestParam(value = "id") Integer id);

    @PostMapping("addnews")
    boolean addnews(News news);

    @GetMapping("selectnewsbyid")
    News selectnewsbyid(@RequestParam(value = "id") Integer id);

    @PostMapping("updatenews")
    boolean updatenews(News news);
}
