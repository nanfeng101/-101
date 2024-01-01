package com.example.demo.service.user;

import com.example.demo.dao.user.UserChatMapper;
import com.example.demo.enity.User_chat;
import com.example.demo.enity.User_chat_user;
import com.example.demo.enity.User_loginlocation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserChatServiceImpl implements UserChatService{
    @Resource
    private UserChatMapper userChatMapper;
    public List<User_chat> getIdUserChat(int id1,int id2){
        List<User_chat> list = userChatMapper.getIdUserChat(id1,id2);
        userChatMapper.updateUserRead(id1,id2);
        for(User_chat item:list) {
            Date birth = item.getSend_time();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:mm");
            String birth1 = dateFormat.format(birth);
            item.setSend_time1(birth1);
        }
        return list;
    }
    public List<User_chat_user> getChatUser(int id){
        List<User_chat_user> list = userChatMapper.getChatUser(id);
        for(User_chat_user uset:list){
            int sum=userChatMapper.getChatUserRead(id,uset.getSend_user_id()).size();
            uset.setSum(sum);
        }
        return list;
    }
    //发送信息
    public void addUserChat(User_chat userChat){
        Date date = new Date();
        userChat.setSend_time(date);
        userChat.setIs_send(1);
        userChatMapper.addUserChat(userChat);
        int id1 = userChat.getUser_id();
        int id2 = userChat.getSend_user_id();
        userChat.setUser_id(id2);
        userChat.setSend_user_id(id1);
        userChat.setIs_send(0);
        userChatMapper.addUserChat(userChat);
//        if(userChatMapper.selectChatUser(userChat.getSend_user_id(),userChat.getUser_id())!=null){
//            userChatMapper.addChatUser(userChat.getSend_user_id(),userChat.getUser_id());
//        }
    }
    public void selectUser_chat_user(int id1,int id2){
        if(userChatMapper.selectUser_chat_user(id1,id2)==null){
            userChatMapper.addChatUser(id1,id2);
        }
    }
    //获取用户私信的总未读信息
    public int getChatRead(int id){
        return userChatMapper.getChatRead(id).size();
    }
}
