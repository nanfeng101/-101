package com.example.demo.service;

import com.example.demo.entity.Bangdan;
import com.example.demo.entity.Song_list;

import java.util.List;
import java.util.Map;

public interface CollectService {
    Map<String,Object> getCollect(int id);
    String deleteCollect(int id);
    String Song_listShouCang(int id1,int id2);
    String MvShouCang(int id1,int id2);
    String Attention_singer(int id1,int id2);

    //安卓
    List<Song_list> getAndroidUserCreateSongList(String phone);

    List<Bangdan> getAndroidUserCollectMusic(String phone);


}
