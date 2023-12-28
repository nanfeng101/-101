package com.example.demo.controller;

import com.example.demo.service.CommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class CommentController {
    @Resource
    private CommentService commentService;
    @GetMapping("/getComment")
    public Map<String,Object> getComment(int id){
        return commentService.getComment(id);
    }
    @GetMapping("/deleteComment")
    public String deleteComment(int id){
        return commentService.deleteComment(id);
    }
    @GetMapping("/getSong_listComment")
    public Map<String,Object> getSong_listComment(int id){
        return commentService.getSong_listComment(id);
    }
    @GetMapping("/deleteSong_ListComment")
    public String deleteSong_ListComment(int id){
        return commentService.deleteSong_ListComment(id);
    }
    @GetMapping("/addComment")
    public void addComment(int user_id,int song_list_id,String content,boolean type){
        System.out.println(user_id);
        System.out.println(1111);
        commentService.addComment(user_id,song_list_id,content,type);
    }
    @GetMapping("/addHuifuComment")
    public void addHuifuComment(int user_id,int song_list_id,String content,boolean type,int up,Integer up_user){
        System.out.println(up_user);
        commentService.addHuifuComment(user_id,song_list_id,content,type,up,up_user);
    }
    @GetMapping("/getMvComment")
    public Map<String,Object> getMvComment(int id){
        return commentService.getMvComment(id);
    }
    @GetMapping("/addMvComment")
    public void addMvComment(int user_id,int song_list_id,String content,boolean type){
        commentService.addMvComment(user_id,song_list_id,content,type);
    }
    @GetMapping("/addMvHuifuComment")
    public void addMvHuifuComment(int user_id,int song_list_id,String content,boolean type,int up,Integer up_user){
        commentService.addMvHuifuComment(user_id,song_list_id,content,type,up,up_user);
    }
    @GetMapping("/getUp_userName")
    public String getUp_userName(int id){
        return commentService.getUp_userName(id);
    }

}
