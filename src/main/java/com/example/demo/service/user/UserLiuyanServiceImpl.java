package com.example.demo.service.user;

import com.example.demo.dao.user.UserLiuyanMapper;
import com.example.demo.enity.User_attention;
import com.example.demo.enity.User_liuyan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class UserLiuyanServiceImpl implements UserLiuyanService{
    @Resource
    private UserLiuyanMapper userLiuyanMapper;
    //获得所有用户的留言
    public List<User_liuyan> getUserLiuyan(){
        return userLiuyanMapper.getUserLiuyan();
    }
    //用户添加留言并判断留言中是否包含敏感词
    public String addUserLiuyan(User_liuyan user_liuyan)throws Exception{
        Date date = new Date();
        user_liuyan.setTime(date);
        List<String> list =new ArrayList<String>();
        int ch;
        boolean rs=false;
        String str="";
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
            String path2 = this.getClass().getClassLoader().getResource("").getPath() + "static/2.txt";
            FileReader reader1 = new FileReader(path2);
            BufferedReader br = new BufferedReader(reader1);
            while ((str = br.readLine()) != null) {
                list.add(str);
            }
            br.close();
        }catch (Exception e){

        }
        if(user_liuyan.getContent()!=null && !user_liuyan.getContent().equals("")){
            int len=user_liuyan.getContent().length();
            for(int i=0;i<len;i++){
                if(rs) {
                    break;
                }
                for(int j=len;j>i;j--){
                    rs = list.contains(user_liuyan.getContent().substring(i,j));
                    if(rs) {
                        break;
                    }
                }
            }
            if(rs) {
                return "您输入的留言中包含敏感词，请重新输入";
            }
            else {
                if (userLiuyanMapper.addUserLiuyan(user_liuyan) > 0) {
                    return "ok";
                }
                return "no";
            }
        }else{
            return "您输入的留言为空,请重新输入";
        }
    }
    //删除留言
    public String deleteLiuyan(int id){
        if(userLiuyanMapper.deleteLiuyan(id)>0){
            return "ok";
        }
        else{
            return "no";
        }
    }
}
