package com.example.demo.controller;

import com.example.demo.entity.Recommend;
import com.example.demo.service.RecommendService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class RecommendController {
    @Resource
    private RecommendService recommendService;
    @GetMapping("/getRecommendSong")
    public List<Recommend> getRecommendSong(String style){
        return recommendService.getRecommendSong(style);
    }
}
