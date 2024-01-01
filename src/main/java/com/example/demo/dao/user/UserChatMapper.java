package com.example.demo.dao.user;

import com.example.demo.enity.User_chat;
import com.example.demo.enity.User_chat_user;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserChatMapper {
    List<User_chat> getIdUserChat(int id1,int id2);
    List<User_chat_user> getChatUser(int id);
    List<User_chat_user> selectChatUser(int id1,int id2);
    void addChatUser(int id1,int id2);
    void addUserChat(User_chat userChat);
    List<User_chat_user> selectUser_chat_user(int id1,int id2);
    List<User_chat> getChatRead(int id);
    List<User_chat> getChatUserRead(int id1,int id2);
    void updateUserRead(int id1,int id2);
}
