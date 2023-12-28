package com.example.demo.entity;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class Song {
    private int id;
    private int singer_id;
    private String name;
    private String singer_name;
    private Date create_time;
    private Date update_time;
    private String introduction;
    private String pic;
    private String lyric;
    private String url;
    private String title;
    private MultipartFile mpfile;
    private int play_sum;
    private int sort_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSinger_id() {
        return singer_id;
    }

    public void setSinger_id(int singer_id) {
        this.singer_id = singer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger_name() {
        return singer_name;
    }

    public void setSinger_name(String singer_name) {
        this.singer_name = singer_name;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MultipartFile getMpfile() {
        return mpfile;
    }

    public void setMpfile(MultipartFile mpfile) {
        this.mpfile = mpfile;
    }

    public int getPlay_sum() {
        return play_sum;
    }

    public void setPlay_sum(int play_sum) {
        this.play_sum = play_sum;
    }

    public int getSort_id() {
        return sort_id;
    }

    public void setSort_id(int sort_id) {
        this.sort_id = sort_id;
    }
}
