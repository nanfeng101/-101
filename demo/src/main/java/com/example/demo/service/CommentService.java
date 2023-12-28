package com.example.demo.service;

import java.util.Date;
import java.util.Map;

public interface CommentService {
    Map<String,Object> getComment(int id);
    String deleteComment(int id);
    Map<String,Object> getSong_listComment(int id);
    String deleteSong_ListComment(int id);
    void addComment(int user_id, int song_list_id, String content, boolean type);
    void addHuifuComment(int user_id, int song_list_id, String content, boolean type,int up,Integer up_user);
    Map<String,Object> getMvComment(int id);
    void addMvComment(int user_id, int song_list_id, String content, boolean type);
    void addMvHuifuComment(int user_id, int song_list_id, String content, boolean type,int up,Integer up_user);
    String getUp_userName(int id);
}
