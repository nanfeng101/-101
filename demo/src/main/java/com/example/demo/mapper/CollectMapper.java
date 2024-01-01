package com.example.demo.mapper;

import com.example.demo.entity.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectMapper {
    List<Collect> getCollect(int id);
    int deleteCollect(int id);

    int Song_listShouCang(int id1,int id2);
    int MvShouCang(int id1,int id2);
    int Attention_singer(int id1,int id2);
    Collect isMvExit(int id1,int id2);
    Collect isSong_listExit(int id1,int id2);
    Collect isSingerExit(int id1,int id2);
    List<Song_list> getAndroidUserCreateSongList(int id1);
    Consumer selectAndroidUserId(String str);
    List<Bangdan> getAndroidUserCollectMusic(int id1);
}
