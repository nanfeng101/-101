package com.example.demo.controller.user;

import com.example.demo.enity.User_chat;
import com.example.demo.enity.User_chat_user;
import com.example.demo.service.user.UserChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class UserChatController {
    @Resource
    private UserChatService userChatService;
    @GetMapping("/getIdUserChat")
    public List<User_chat> getIdUserChat(int id1,int id2){
        return userChatService.getIdUserChat(id1,id2);
    }
    @GetMapping("/getChatUser")
    public List<User_chat_user> getChatUser(int id){
        return userChatService.getChatUser(id);
    }
    @PostMapping("/addUserChat")
    public void addUserChat(@RequestBody User_chat userChat){
        userChatService.addUserChat(userChat);
    }
    @GetMapping("/selectUser_chat_user")
    public void selectUser_chat_user(int id1,int id2){
        userChatService.selectUser_chat_user(id1,id2);
    }
    @GetMapping("/getChatRead")
    public int getChatRead(int id){
        return userChatService.getChatRead(id);
    }
}
