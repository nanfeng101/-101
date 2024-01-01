package com.example.demo.service.user;

import com.example.demo.enity.User;
import com.example.demo.enity.User_loginlocation;
import com.example.demo.enity.User_search_history;

import javax.servlet.http.HttpServletRequest;
import javax.swing.event.ListDataEvent;
import java.util.List;

public interface UserDetailService {
    User login(User user)throws Exception;
    String register(User user);
    List<User_loginlocation> getLoginLocation(int id);
    String updatedetail(User user);
    User getUserDetail(int id);
    List<User> getAllUser();
    String updateUserDisable(int id,boolean disable);
    List<User> searchUsername(String username);
    List<User_search_history> getUser_search_history(int id);
    void addUser_search_history(User_search_history user_search_history);
}
