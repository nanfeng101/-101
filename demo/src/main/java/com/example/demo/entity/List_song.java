package com.example.demo.entity;

public class List_song {
    private int id;
    private int song_id;
    private String name;
    private int song_list_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSong_id() {
        return song_id;
    }

    public void setSong_id(int song_id) {
        this.song_id = song_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSong_list_id() {
        return song_list_id;
    }

    public void setSong_list_id(int song_list_id) {
        this.song_list_id = song_list_id;
    }
}
