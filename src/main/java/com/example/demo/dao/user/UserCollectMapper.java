package com.example.demo.dao.user;

import com.example.demo.enity.User_browse;
import com.example.demo.enity.User_browse_current;
import com.example.demo.enity.User_collect_name;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCollectMapper {
    List<User_collect_name> selectCollectName(int id);
    int addCollect(User_collect_name user_collect_name);
    List<User_collect_name> addCollect_text(int id,String name);
    int updateCollect_name(User_collect_name user_collect_name);
    User_collect_name selectMiaoshuCollect(String name);
    User_collect_name selectNameCollect(String name);
    int deleteCollect(int user_id,int text_id);
    //用于浏览记录的查询和添加
    User_browse selectIsBrowse(int user_id,int text_id);
    void addUserBrowse(User_browse user_browse);
    List<User_browse> getUserBrowse(User_browse_current user_browse_current);
    void updateBrowseTime(User_browse user_browse);
    List<User_browse> getBrowserTime(User_browse_current user_browse_current);

}
