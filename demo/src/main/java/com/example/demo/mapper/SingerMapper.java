package com.example.demo.mapper;

import com.example.demo.entity.Singer;
import com.example.demo.entity.SingerByCon;
import com.example.demo.entity.Song;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SingerMapper {
    List<Singer> selectSingerPage(SingerByCon singerByCon);

    List<Singer> selectSinger(String name);
    void updatePic(String pic,int id);
    int addSinger(Singer singer);
    int deleteSinger(int id);
    Singer getIdSinger(int id);
    Singer getNameSinger(String name);
    int updateSinger(Singer singer);
    Singer searchNameId(String name);
    List<Singer> getSexSingerPage(SingerByCon singerByCon);
    List<Singer> getSong_to_qiantai(int id);
}
