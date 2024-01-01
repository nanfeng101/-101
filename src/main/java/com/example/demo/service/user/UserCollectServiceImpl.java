package com.example.demo.service.user;

import com.example.demo.dao.user.UserCollectMapper;
import com.example.demo.enity.User_browse;
import com.example.demo.enity.User_browse_current;
import com.example.demo.enity.User_collect_name;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserCollectServiceImpl implements UserCollectService{
    @Resource
    private UserCollectMapper userCollectMapper;
    //查询用户创建的收藏夹
    public List<User_collect_name> selectCollectName(int id){
        return userCollectMapper.selectCollectName(id);
    }
    //用户创建收藏夹
    public String addCollect(User_collect_name user_collect_name){
        if(user_collect_name.getCollect_miaoshu()==null){
            user_collect_name.setCollect_miaoshu("清添加收藏夹描述");
        }
        if(userCollectMapper.addCollect(user_collect_name)>0){
            return "ok";
        }
        return "no";
    }
    //获取收藏夹中的文章信息
    public List<User_collect_name> addCollect_text(int id,String name){
        return userCollectMapper.addCollect_text(id,name);
    }
    //修改收藏夹信息
    public String updateCollect_name(User_collect_name user_collect_name){
        if(user_collect_name.getCollect_name()!=null){
            user_collect_name.setId(userCollectMapper.selectNameCollect(user_collect_name.getCollect_name()).getId());
            if(userCollectMapper.updateCollect_name(user_collect_name)>0){
                return "ok";
            }
            return "no";
        }
        else if(user_collect_name.getCollect_miaoshu()!=null){
            user_collect_name.setId(userCollectMapper.selectMiaoshuCollect(user_collect_name.getCollect_miaoshu()).getId());
            if(userCollectMapper.updateCollect_name(user_collect_name)>0){
                return "ok";
            }
            return "no";
        }
        else {
            return "no";
        }
    }
    //删除收藏
    public String deleteCollect(int user_id,int text_id){
        if(userCollectMapper.deleteCollect(user_id,text_id)>0){
            return "ok";
        }
        else{
            return "no";
        }
    }
    //获取用户浏览记录
    public Map<String,Object> getUserBrowse(User_browse_current user_browse_current){
        Map<String,Object> map = new HashMap<String,Object>();
        int a=user_browse_current.getPage();
        int sum=a*(user_browse_current.getCurrent()-1);
        user_browse_current.setCurrent(sum);
        user_browse_current.setAct("byPage");
        List<User_browse> list1 = userCollectMapper.getUserBrowse(user_browse_current);
        List<User_browse> list2=userCollectMapper.getBrowserTime(user_browse_current);
        for(User_browse item:list2){
            Date birth = item.getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String birth1 = dateFormat.format(birth);
            item.setTime1(birth1);
        }
        for(User_browse item:list1){
            Date birth = item.getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String birth1 = dateFormat.format(birth);
            item.setTime1(birth1);
        }
        map.put("user_browse",list1);
        map.put("time",list2);
        user_browse_current.setAct("byNoPage");
        map.put("sum",userCollectMapper.getUserBrowse(user_browse_current).size());
        return map;
    }
    //添加用户记录
    public void addUserBrowse(User_browse user_browse){
        Date date = new Date();
        user_browse.setTime(date);
        if(userCollectMapper.selectIsBrowse(user_browse.getUser_id(),user_browse.getText_id())==null){
            userCollectMapper.addUserBrowse(user_browse);
        }
        else{
            userCollectMapper.updateBrowseTime(user_browse);
        }
    }
}
