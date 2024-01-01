package com.example.demo.dao.user;

import com.example.demo.enity.User;
import com.example.demo.enity.User_loginlocation;
import com.example.demo.enity.User_search_history;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDetailMapper {
    User login(User user);
    User selectUserPhone(User user);
    int register(User user);
    void addLoginLocation(User_loginlocation user_loginlocation);
    List<User_loginlocation> getLoginLocation(int id);
    int updatedetail(User user);
    User getUserDetail(int id);
    User selectphoneUser(String phone);
    User selectemailUser(String email);
    void addCollect_name(int id);
    List<User> getAllUser();
    int updateUserDisable(boolean disable,int id);
    void addChatUser(int id1);
    List<User> searchUsername(String username);
    List<User_search_history> getUser_search_history(int id);
    void addUser_search_history(User_search_history user_search_history);
    User_search_history searchaddUser_search_history(User_search_history user_search_history);
    void deleteUser_search_history(int id);
}
