package com.example.demo.mapper;

import com.example.demo.entity.SearchSong;
import com.example.demo.entity.Singer;
import com.example.demo.entity.Song;
import com.example.demo.entity.SongByCon;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SongMapper {
    List<Song> getSongPage(SongByCon songByCon);
    List<Song> searchSongName(String val);
    List<Song> searchSongSinger_id(int id);
    void updateSongPic(String pic,int id);
    int deleteSong(int id);
    Song getIdSong(int id);
    int updateSong(Song song);
    int addSong(Song song);
    void updateSongUrl(String url,int id);
    List<Song> getSong_list_to_qianttai2(int id);
    List<Song> getSong_to_qiantai2(int id);
    int getPlay_sum(int id);
    void updatePlay_sum(int id,int sum);
    List<Song> getSongPlay();
}
