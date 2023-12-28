package com.example.demo.service;

import com.example.demo.entity.Recommend;

import java.util.List;
import java.util.Map;

public interface RecommendService {
    List<Recommend> getRecommendSong(String style);
}
