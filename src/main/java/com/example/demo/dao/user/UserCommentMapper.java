package com.example.demo.dao.user;

import com.example.demo.enity.User_comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCommentMapper {
    List<User_comment> getUserComment(int id);
    int addUserComment(User_comment userComment);
}
