package com.example.demo.service;

import java.util.List;
import java.util.Map;

public interface SystemhomeService {
    int getUserSum();
    int getSongSum();
    int getSingerSum();
    int getSong_listSum();
    int selectUserSex(int sex);
    int selectSingerSex(int sex);
    List<Integer> selectSingerLocation();
    List<Integer> selectSong_listStyle();
    Map<String,Object> getSongMvPlaySum();
}
