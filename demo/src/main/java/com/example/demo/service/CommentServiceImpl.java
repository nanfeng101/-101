package com.example.demo.service;

import com.example.demo.entity.Comment;
import com.example.demo.mapper.CommentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService{
    @Resource
    private CommentMapper commentMapper;
    //歌曲获取评论信息
    public Map<String,Object> getComment(int id){
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("comment",commentMapper.getComment(id));
        return map1;
    }
    //歌曲删除评论
    public String deleteComment(int id){
        if (commentMapper.deleteComment(id)>0){
            return "ok";
        }
        return "no";
    }
    //歌单获取评论信息
    public Map<String,Object> getSong_listComment(int id){
        Map<String,Object> map1 = new HashMap<String,Object>();
        List<Comment> list2 = commentMapper.getSong_listComment(id);
        for(Comment item1:list2){
            Date birth = item1.getCreate_time();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String birth1=dateFormat.format(birth);
            item1.setData(birth1);
        }
        map1.put("song_list",list2);
        return map1;
    }
    //歌单删除评论
    public String deleteSong_ListComment(int id){
        if (commentMapper.deleteSong_ListComment(id)>0){
            return "ok";
        }
        return "no";
    }
    //添加歌单评论
    public void addComment(int user_id,int song_list_id,String content,boolean type){
        Date date = new Date();
        commentMapper.addComment(user_id,song_list_id,content,type,date);
    }
    //添加歌单回复评论
    public void addHuifuComment(int user_id,int song_list_id,String content,boolean type,int up,Integer up_user){
        Date date = new Date();
        commentMapper.addHuifuComment(user_id,song_list_id,content,type,date,up,up_user);
    }
    //获取mv评论
    public Map<String,Object> getMvComment(int id){
        Map<String,Object> map1 = new HashMap<String,Object>();
        List<Comment> list2 = commentMapper.getMvComment(id);
        for(Comment item1:list2){
            Date birth = item1.getCreate_time();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String birth1=dateFormat.format(birth);
            item1.setData(birth1);
        }
        map1.put("song_list",list2);
        return map1;
    }
    //添加mv评论
    public void addMvComment(int user_id,int song_list_id,String content,boolean type){
        Date date = new Date();
        commentMapper.addMvComment(user_id,song_list_id,content,type,date);
    }
    //添加mv回复评论
    public void addMvHuifuComment(int user_id,int song_list_id,String content,boolean type,int up,Integer up_user){
        Date date = new Date();
        commentMapper.addMvHuifuComment(user_id,song_list_id,content,type,date,up,up_user);
    }
    //根据up_user获得用户回复的那个人的用户名
    public String getUp_userName(int id){
        if(id!=0) {
            int a = commentMapper.getUser_id(id);
            String name = commentMapper.getUserName(a);
            return name;

        }
        else{
            return "";
        }
    }

}
