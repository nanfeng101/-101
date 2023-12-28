package com.example.demo.service;

import com.example.demo.entity.Bangdan;
import com.example.demo.entity.Song;
import com.example.demo.entity.Song_list;
import com.example.demo.entity.Song_listByCon;

import java.util.List;
import java.util.Map;

public interface Song_listService {
    Map<String,Object> getSong_listPage(Song_listByCon song_listByCon);
    void updateSong_listPic(String pic,int id);
    Song_list getIdSong_list(int id);
    String updateSong_list(Song_list songList);
    String deleteSong_list(int id);
    String addSong_list(Song_list songList);
    Map<String,Object> selectTitleSong_list(String title);
    Map<String,Object> getSong_listContent(int id);
    String deleteSong_ListSong(int id);
    Map<String,Object> getSong_list_to_qianttai(int id);
    List<Song_list> getAndroidSong_listPage(Song_listByCon song_listByCon);
    public List<Bangdan> getAndroidSong_listMusic(int id);
}
