package com.example.demo.service.user;

import com.example.demo.dao.user.UserAttentionMapper;
import com.example.demo.dao.user.UserDetailMapper;
import com.example.demo.enity.*;
import com.example.demo.vo.BiaoqianVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserAttentionServiceImpl implements UserAttentionService {
    @Resource
    private UserAttentionMapper userAttentionMapper;
    @Resource
    private UserDetailMapper userDetailMapper;
    //获取用户关注
    public List<User_attention> getUserAttention(int id){
        return userAttentionMapper.getUserAttention(id);
    }
    //添加用户关注和用户粉丝
    public String addUserAttention(int user_id,int attention_id){
        if(userAttentionMapper.selectUserAttention(attention_id,user_id)==null) {
            userAttentionMapper.addUserFensi(attention_id,user_id);
            if (userAttentionMapper.addUserAttention(user_id, attention_id) > 0) {
                return "ok";
            }
            return "no";
        }else{
            return "no";
        }
    }
    //删除用户关注和用户粉丝
    public String  deleteUserAttention(int user_id,int attention_id){
        if(userAttentionMapper.deleteUserAttention(user_id,attention_id)>0){
            userAttentionMapper.deleteUserFensi(attention_id,user_id);
            return "ok";
        }
        return "no";
    }
    //获取用户粉丝
    public List<User_fensi> getUserFensi(int id){
        return userAttentionMapper.getUserFensi(id);
    }
    //获取用户关注发布的文章
    public List<Text> getAttentionTextDetail(int id){
        List<Text> list = new ArrayList<>();
        List<Text> list1=new ArrayList<>();
        List<User_attention> list2 = new ArrayList<>();
        list2=userAttentionMapper.getUserAttention(id);
        for(User_attention item:list2){
            if(userAttentionMapper.getAttentionTextDetail(item.getAttention_id()).size()!=0) {
                list1 = userAttentionMapper.getAttentionTextDetail(item.getAttention_id());
                for (Text item1 : list1) {
                    list.add(item1);
                }
            }
        }
        for(Text item1:list){
            User user = userDetailMapper.getUserDetail(item1.getUser_id());
            item1.setUsermame(user.getUsername());
            item1.setUserpic(user.getPic());
            Date birth = item1.getCreate_time();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String birth1=dateFormat.format(birth);
            item1.setCreate_time1(birth1);
        }
        return list;
     }
}
