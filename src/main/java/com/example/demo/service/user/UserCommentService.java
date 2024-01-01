package com.example.demo.service.user;

import com.example.demo.enity.User_comment;

import java.util.List;

public interface UserCommentService {
    List<User_comment> getUserComment(int id);
    String addUserComment(User_comment userComment)throws Exception;
}
