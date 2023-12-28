package com.example.demo.service;

import com.example.demo.entity.Bangdan;
import com.example.demo.entity.SongByCon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BangdanService {
    List<Bangdan> getBangSong(String bangname);
    Map<String,Object> getTopSong(SongByCon songByCon);
}
