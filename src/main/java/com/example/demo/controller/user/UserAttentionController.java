package com.example.demo.controller.user;

import com.example.demo.enity.Text;
import com.example.demo.enity.User_attention;
import com.example.demo.enity.User_fensi;
import com.example.demo.service.user.UserAttentionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserAttentionController {
    @Resource
    private UserAttentionService userAttentionService;
    @GetMapping("/getUserAttention")
    public List<User_attention> getUserAttention(int id){
        return userAttentionService.getUserAttention(id);
    }
    @GetMapping("/addUserAttention")
    public String addUserAttention(int user_id,int attention_id){
        return userAttentionService.addUserAttention(user_id,attention_id);
    }
    @GetMapping("/deleteUserAttention")
    public String deleteUserAttention(int user_id,int attention_id){
        return userAttentionService.deleteUserAttention(user_id,attention_id);
    }
    @GetMapping("/getUserFensi")
    public List<User_fensi> getUserFensi(int id){
        return userAttentionService.getUserFensi(id);
    }
    @GetMapping("/getAttentionTextDetail")
    public List<Text> getAttentionTextDetail(int id){
        return userAttentionService.getAttentionTextDetail(id);
    }
}
