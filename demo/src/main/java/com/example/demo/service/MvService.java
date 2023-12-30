package com.example.demo.service;

import com.example.demo.entity.Mv;
import com.example.demo.entity.MvByCon;

import java.util.List;
import java.util.Map;

public interface MvService {
    Map<String ,Object> getMvDetail(MvByCon mvByCon);
    List<Mv> classifymv(String name);
    void updateMvPic(String pic,int id);
    String addMv(Mv mv)throws Exception;
    String deleteMv(int id)throws Exception;
    Mv selectIdMv(int id);
    String updateMv(Mv mv);

    List<Mv> getAndroidMvDetail();
}
