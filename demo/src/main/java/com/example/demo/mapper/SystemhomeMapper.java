package com.example.demo.mapper;

import com.example.demo.entity.Consumer;
import com.example.demo.entity.Singer;
import com.example.demo.entity.Song_list;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemhomeMapper {
    List<Consumer> selectUserSex(int sex);
    List<Singer> selectSingerSex(int sex);
    List<Song_list> selectSingerLocation1(String style);
    List<Singer> selectSingerLocation(String location);
}
