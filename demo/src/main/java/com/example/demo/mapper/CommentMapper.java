package com.example.demo.mapper;

import com.example.demo.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CommentMapper {
    List<Comment> getComment(int id);
    int deleteComment(int id);
    List<Comment> getSong_listComment(int id);
    int deleteSong_ListComment(int id);
    int getUser_id(int id);
    String getUserName(int id);
    void addComment(int user_id, int song_list_id, String content, boolean type, Date creat_time);
    void addHuifuComment(int user_id, int song_list_id, String content, boolean type, Date creat_time,int up,Integer up_user);
    List<Comment> getMvComment(int id);
    void addMvComment(int user_id, int song_list_id, String content, boolean type, Date creat_time);
    void addMvHuifuComment(int user_id, int song_list_id, String content, boolean type, Date creat_time,int up,Integer up_user);

}
