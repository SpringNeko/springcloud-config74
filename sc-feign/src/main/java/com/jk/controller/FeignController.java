package com.jk.controller;

import com.jk.model.News;
import com.jk.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller

public class FeignController {

    @Autowired
    private FeignService feignService;

    @GetMapping("test/{hello}")
    @ResponseBody
    public String test(@PathVariable(value = "hello")String hello){
        return feignService.test(hello);

    }

    @GetMapping("findnews")
    @ResponseBody
    public HashMap<String,Object> findnews(@RequestParam(value = "start") Integer start,
                                           @RequestParam(value ="pageSize") Integer pageSize){
        return feignService.findnews(start,pageSize);
    }

    @GetMapping("deletenews")
    @ResponseBody
    public boolean deletenews (@RequestParam(value = "id") Integer id){
        return feignService.deletenews(id);
    }

    @PostMapping("addnews")
    @ResponseBody
    public boolean addnews(News news){

        return feignService.addnews(news);
    }

    @GetMapping("selectnewsbyid")
    @ResponseBody
    public News selectnewsbyid(@RequestParam(value = "id") Integer id){
        return feignService.selectnewsbyid(id);
    }

    @PostMapping("updatenews")
    @ResponseBody
    public boolean updatenews(News news){
        return feignService.updatenews(news);
    }
    @RequestMapping("tolist")
    public String tolist(){
        return "list";
    }
    @RequestMapping("toaddnews")

    public String toaddnews(){
        return "addnews";
    }
    @RequestMapping("toupdnews")

    public String toupdnews(@RequestParam(value = "id") Integer id, ModelMap modelMap){
       News selectnewsbyid = selectnewsbyid(id);
       modelMap.put("news",selectnewsbyid);
        return "updnews";
    }
}
