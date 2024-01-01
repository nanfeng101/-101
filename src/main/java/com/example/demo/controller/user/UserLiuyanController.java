package com.example.demo.controller.user;

import com.example.demo.enity.User_liuyan;
import com.example.demo.service.user.UserLiuyanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserLiuyanController {
    @Resource
    private UserLiuyanService userLiuyanService;
    @GetMapping("/getUserLiuyan")
    public List<User_liuyan> getUserLiuyan(){
        return userLiuyanService.getUserLiuyan();
    }
    @PostMapping("/addUserLiuyan")
    public String addUserLiuyan(@RequestBody User_liuyan user_liuyan)throws Exception{
        return userLiuyanService.addUserLiuyan(user_liuyan);
    }
    @GetMapping("/deleteLiuyan")
    public String deleteLiuyan(int id){
        return userLiuyanService.deleteLiuyan(id);
    }
}
