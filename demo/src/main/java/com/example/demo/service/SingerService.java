package com.example.demo.service;

import com.example.demo.entity.Singer;
import com.example.demo.entity.SingerByCon;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface SingerService {
    Map<String,Object> selectSingerPage(SingerByCon singerByCon);
    Map<String,Object> selectSinger(String name);
    void updatePic(String pic,int id);
    String addSinger(Singer singer);
    String deleteSinger(int id);
    Singer getIdSinger(int id);
    String updateSinger(Singer singer);
    Map<String,Object> getSong_to_qiantai(int id);
}
