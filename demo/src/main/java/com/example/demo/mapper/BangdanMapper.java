package com.example.demo.mapper;

import com.example.demo.entity.Bangdan;
import com.example.demo.entity.Song;
import com.example.demo.entity.SongByCon;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BangdanMapper {
    List<Bangdan> getDouyin();
    List<Bangdan> getBiao();
    List<Song> getTopSong(SongByCon songByCon);
}
