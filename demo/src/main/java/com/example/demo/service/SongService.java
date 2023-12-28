package com.example.demo.service;
import com.example.demo.entity.SearchSong;
import com.example.demo.entity.Singer;
import com.example.demo.entity.Song;
import com.example.demo.entity.SongByCon;

import java.util.List;
import java.util.Map;

public interface SongService {
    Map<String,Object> getSongPage(SongByCon songByCon);
    void updateSongPic(String pic,int id);
    String deleteSong(int id);
    Song getIdSong(int id);
    String updateSong(Song song);
    String addSong(Song song)throws Exception;
    void updateSongUrl(String url,int id);
    Map<String,Object> searchSong(SearchSong searchSong);
    List<Song> classifysong(String name);
    void updatePlay_sum(int id);
}
