package com.example.demo.service.user;

import com.example.demo.dao.user.UserTextMapper;
import com.example.demo.enity.Text;
import com.example.demo.enity.Text_draft;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserTextServiceImpl implements UserTextService{
    @Resource
    private UserTextMapper userTextMapper;
    //获取用户写的文章
    public List<Text> getUserIdText(int id){
        List<Text> list = userTextMapper.getUserIdText(id);
        for(Text item1:list){
            Date birth = item1.getCreate_time();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:mm");
            String birth1=dateFormat.format(birth);
            item1.setCreate_time1(birth1);
        }
        return list;
    }
    //根据日期获取用户发布的文章
    public List<Text> selectDateText(int id){
        List<Text> list = userTextMapper.selectDateText(id);
        for(Text item1:list){
            Date birth = item1.getCreate_time();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:mm");
            String birth1=dateFormat.format(birth);
            item1.setCreate_time1(birth1);
        }
        return list;
    }
    //根据观看量获取用户发布的文章
    public List<Text> selectViewText(int id){
        List<Text> list = userTextMapper.selectViewText(id);
        for(Text item1:list){
            Date birth = item1.getCreate_time();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:mm");
            String birth1=dateFormat.format(birth);
            item1.setCreate_time1(birth1);
        }
        return list;
    }
    //获取文章草稿内容
    public List<Text_draft> getUserIdTextDraft(int id){
        return userTextMapper.getUserIdTextDraft(id);
    }
}
