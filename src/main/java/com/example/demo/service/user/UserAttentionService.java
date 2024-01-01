package com.example.demo.service.user;

import com.example.demo.enity.Text;
import com.example.demo.enity.User_attention;
import com.example.demo.enity.User_fensi;

import java.util.List;

public interface UserAttentionService {
    List<User_attention> getUserAttention(int id);
    String addUserAttention(int user_id,int attention_id);
    String  deleteUserAttention(int user_id,int attention_id);
    List<User_fensi> getUserFensi(int id);
    List<Text> getAttentionTextDetail(int id);
}
