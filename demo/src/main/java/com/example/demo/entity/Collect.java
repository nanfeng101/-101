package com.example.demo.entity;

import java.util.Date;

public class Collect {
    private int id;
    private int user_id;
    private String name;
    private int type;
    private int song_id;
    private int song_list_id;
    private Date create_time;
    private int mv_id;
    private int singer_id;
    private int fans_id;
    private int attention_user_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSong_id() {
        return song_id;
    }

    public void setSong_id(int song_id) {
        this.song_id = song_id;
    }

    public int getSong_list_id() {
        return song_list_id;
    }

    public void setSong_list_id(int song_list_id) {
        this.song_list_id = song_list_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public int getMv_id() {
        return mv_id;
    }

    public void setMv_id(int mv_id) {
        this.mv_id = mv_id;
    }

    public int getSinger_id() {
        return singer_id;
    }

    public void setSinger_id(int singer_id) {
        this.singer_id = singer_id;
    }

    public int getFans_id() {
        return fans_id;
    }

    public void setFans_id(int fans_id) {
        this.fans_id = fans_id;
    }

    public int getAttention_user_id() {
        return attention_user_id;
    }

    public void setAttention_user_id(int attention_user_id) {
        this.attention_user_id = attention_user_id;
    }
}
