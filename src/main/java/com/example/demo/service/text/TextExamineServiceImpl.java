package com.example.demo.service.text;

import com.example.demo.dao.text.TextExamineMapper;
import com.example.demo.enity.Text_draft;
import com.example.demo.enity.User_chat;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TextExamineServiceImpl implements TextExamineService{
    @Resource
    private TextExamineMapper textExamineMapper;
    //通过审核文章
    public String updateExamine(int id){
        if(textExamineMapper.updateExamine(id)>0){
            User_chat userChat=new User_chat();
            Date date = new Date();
            userChat.setSend_time(date);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:mm");
            String birth1 = dateFormat.format(date);
            userChat.setMessage("恭喜您，您的文章已经在"+birth1+"成功通过审核");
            userChat.setSend_user_id(5);
            userChat.setIs_send(0);
            textExamineMapper.addUserChat(userChat);
            return "ok";
        }
        else{
            return "no";
        }
    }
    //驳回审核文章
    public String closeReason(User_chat user_chat){
        Text_draft text_draft = textExamineMapper.selectIdText(user_chat.getText_id());
        Date date = new Date();
        user_chat.setMessage("您的文章<"+text_draft.getBiaoti()+">审核失败,由于您的"+user_chat.getMessage());
        user_chat.setSend_time(date);
        user_chat.setIs_send(0);
        user_chat.setSend_user_id(5);
        textExamineMapper.addUserChat(user_chat);
        //删除文章状态
        textExamineMapper.deleteExamine(user_chat.getText_id());
        //将文章存放到草稿箱
        textExamineMapper.addTextDraft(text_draft);
        textExamineMapper.deleteText(user_chat.getText_id());
        return "ok";
    }
}
