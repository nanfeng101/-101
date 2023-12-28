package com.example.demo.mapper;

import com.example.demo.entity.Mv;
import com.example.demo.entity.MvByCon;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MvMapper {
    List<Mv> getMvDetail(MvByCon mvByCon);
    List<Mv> classifymv(String name);
    void updateMvPic(String pic,int id);
    int addMv(Mv mv);
    int deleteMv(int id);
    Mv selectIdMv(int id);
    int updateMv(Mv mv);
    List<Mv> getMvPlay();
}
