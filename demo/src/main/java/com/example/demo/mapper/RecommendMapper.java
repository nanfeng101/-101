package com.example.demo.mapper;

import com.example.demo.entity.Recommend;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendMapper {
    List<Recommend> getRecommendSong(String style);
}
