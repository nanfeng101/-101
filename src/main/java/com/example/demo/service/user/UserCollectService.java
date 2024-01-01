package com.example.demo.service.user;

import com.example.demo.enity.User_browse;
import com.example.demo.enity.User_browse_current;
import com.example.demo.enity.User_collect_name;

import java.util.List;
import java.util.Map;

public interface UserCollectService {
    List<User_collect_name> selectCollectName(int id);
    String addCollect(User_collect_name user_collect_name);
    List<User_collect_name> addCollect_text(int id,String name);
    String updateCollect_name(User_collect_name user_collect_name);
    String deleteCollect(int user_id,int text_id);
    Map<String,Object> getUserBrowse(User_browse_current user_browse_current);
    void addUserBrowse(User_browse user_browse);
}
