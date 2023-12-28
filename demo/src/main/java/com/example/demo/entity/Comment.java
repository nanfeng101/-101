package com.example.demo.entity;

import java.util.Date;

public class Comment {
    private int id;
    private int user_id;
    private String username;
    private int mv_id;
    private int song_id;
    private String location;
    private int song_list_id;
    private String content;
    private Date create_time;
    private boolean type;
    private int up;
    private int up_user;
    private String avator;
    private String data;
    private boolean show1;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getMv_id() {
        return mv_id;
    }

    public void setMv_id(int mv_id) {
        this.mv_id = mv_id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getUp_user() {
        return up_user;
    }

    public void setUp_user(int up_user) {
        this.up_user = up_user;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isShow1() {
        return show1;
    }

    public void setShow1(boolean show1) {
        this.show1 = show1;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
