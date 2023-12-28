package com.example.demo.mapper;

import com.example.demo.entity.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Song_listMapper {
    List<Song_list> getSong_listPage(Song_listByCon song_listByCon);
    void updateSong_listPic(String pic,int id);
    Song_list getIdSong_list(int id);
    int updateSong_list(Song_list songList);
    int deleteSong_list(int id);
    int addSong_list(Song_list songList);
    List<Song_list> selectTitleSong_list(String title);
    int deleteSong_ListSong(int id);
    List<List_song> getSong_listContent(int id);
    List<Song_list> getSong_list_to_qianttai(int id);
    List<Song_list> getStyleSong_list(Song_listByCon song_listByCon);
    List<Song_list> getAndroidSong_listPage(Song_listByCon song_listByCon);
    List<Bangdan> getAndroidSong_listMusic(int id);

}
