package com.example.andorid_project.shouye.enity;

import android.media.MediaPlayer;

import com.example.andorid_project.shouye.geci.LrcBean;

import java.util.List;

public class Bangdan {
    private int id1;
    private int song_id;
    private int sort_id;
    private int biao_id;
    private int id;
    private String name;
    private String pic;
    private String lyric;
    private String url;
    private String introduction;
    private String singer_name;
    private MediaPlayer mediaPlayer;
    private List<LrcBean> lrcBeanList;

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public int getSong_id() {
        return song_id;
    }

    public void setSong_id(int song_id) {
        this.song_id = song_id;
    }

    public int getSort_id() {
        return sort_id;
    }

    public void setSort_id(int sort_id) {
        this.sort_id = sort_id;
    }

    public int getBiao_id() {
        return biao_id;
    }

    public void setBiao_id(int biao_id) {
        this.biao_id = biao_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getSinger_name() {
        return singer_name;
    }

    public void setSinger_name(String singer_name) {
        this.singer_name = singer_name;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public List<LrcBean> getLrcBeanList() {
        return lrcBeanList;
    }

    public void setLrcBeanList(List<LrcBean> lrcBeanList) {
        this.lrcBeanList = lrcBeanList;
    }
}
