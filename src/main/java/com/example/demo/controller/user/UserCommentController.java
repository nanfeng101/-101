package com.example.demo.controller.user;

import com.example.demo.enity.User_comment;
import com.example.demo.service.user.UserCommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserCommentController {
    @Resource
    private UserCommentService userCommentService;
    @GetMapping("/getUserComment")
    public List<User_comment> getUserComment(int id){
        return userCommentService.getUserComment(id);
    }
    @PostMapping("/addUserComment")
    public String addUserComment(@RequestBody User_comment userComment)throws Exception{
        return userCommentService.addUserComment(userComment);
    }
}
