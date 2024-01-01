package com.example.demo.service.user;

import com.example.demo.dao.user.UserCommentMapper;
import com.example.demo.enity.User_comment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserCommentServiceImpl implements UserCommentService{
    @Resource
    private UserCommentMapper userCommentMapper;
    public List<User_comment> getUserComment(int id){
        List<User_comment> list = userCommentMapper.getUserComment(id);
        for(User_comment item:list){
            Date birth = item.getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String birth1 = dateFormat.format(birth);
            item.setTime1(birth1);
        }
        return list;
    }
    //添加评论并判断评论中是否有敏感词
    public String addUserComment(User_comment userComment)throws Exception{
        Date date = new Date();
        userComment.setTime(date);
        userComment.setCreate_time(date);
        if(userComment.getI()==1){
            userComment.setType(false);
        }
        else if(userComment.getI()==2){
            userComment.setType(true);
        }
        else{
            userComment.setType(true);
        }
        //判断评论中是否包含敏感词
        //将文件中的敏感词库读取存放到集合里
        List<String> list = new ArrayList<String>();
        int ch;
        boolean rs = false;
        String str = "";
        try {
            String path1 = this.getClass().getClassLoader().getResource("").getPath() + "static/key.txt";
            FileReader reader = new FileReader(path1);
            while ((ch = reader.read()) != -1) {
                if ((char) ch == '|') {
                    list.add(str);
                    str = "";
                    continue;
                }
                str = str + (char) ch;
            }
            reader.close();
        }catch (Exception e){

        }
        try {
            String path2 = this.getClass().getClassLoader().getResource("").getPath() + "static/2.txt";
            FileReader reader1 = new FileReader(path2);
            BufferedReader br = new BufferedReader(reader1);
            while ((str = br.readLine()) != null) {
                list.add(str);
            }
            br.close();
        }catch (Exception e){

        }
        if(userComment.getContent()!=null && !userComment.getContent().equals("")){
            int len=userComment.getContent().length();
            for(int i=0;i<len;i++){
                if(rs) {
                    break;
                }
                for(int j=len;j>i;j--){
                    rs = list.contains(userComment.getContent().substring(i,j));
                    if(rs) {
                        break;
                    }
                }
            }
            if(rs) {
                return "您输入的留言中包含敏感词，请重新输入";
            }
            else {
                if(userCommentMapper.addUserComment(userComment)>0){
                    return "ok";
                }
                else{
                    return "no";
                }
            }
        }else{
            return "您输入的留言为空,请重新输入";
        }
    }
}
