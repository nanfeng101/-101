package com.example.demo.service.user;

import com.example.demo.enity.User_liuyan;

import java.util.List;

public interface UserLiuyanService {
    List<User_liuyan> getUserLiuyan();
    String addUserLiuyan(User_liuyan user_liuyan)throws Exception;
    String deleteLiuyan(int id);
}
