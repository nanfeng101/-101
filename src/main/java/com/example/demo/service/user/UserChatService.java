package com.example.demo.service.user;

import com.example.demo.enity.User;
import com.example.demo.enity.User_chat;
import com.example.demo.enity.User_chat_user;

import java.util.List;
import java.util.Map;

public interface UserChatService {
    List<User_chat> getIdUserChat(int id1,int id2);
    List<User_chat_user> getChatUser(int id);
    void addUserChat(User_chat userChat);
    void selectUser_chat_user(int id1,int id2);
    int getChatRead(int id);
}
