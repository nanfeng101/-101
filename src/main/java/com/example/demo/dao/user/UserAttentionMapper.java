package com.example.demo.dao.user;

import com.example.demo.enity.Text;
import com.example.demo.enity.User;
import com.example.demo.enity.User_attention;
import com.example.demo.enity.User_fensi;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAttentionMapper {
    List<User_attention> getUserAttention(int id);
    int addUserAttention(int user_id,int attention_id);
    int deleteUserAttention(int user_id,int attention_id);
    User_attention selectUserAttention(int id,int user_id);
    int addUserFensi(int user_id,int fensi_id);
    int deleteUserFensi(int user_id,int fensi_id);
    List<User_fensi> getUserFensi(int id);
    List<Text> getAttentionTextDetail(int id);
}
