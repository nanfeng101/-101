package com.example.demo.controller.recommend;

import com.example.demo.service.recommend.RecommendService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class RecommendController {
    @Resource
    private RecommendService recommendService;
    @GetMapping("/getUserRecommend")
    public Map<String,Object> getUserRecommend(int id){
        return recommendService.getUserRecommend(id);
    }
}
