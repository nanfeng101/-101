package com.example.demo.dao.user;

import com.example.demo.enity.User_liuyan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLiuyanMapper {
    List<User_liuyan> getUserLiuyan();
    int addUserLiuyan(User_liuyan user_liuyan);
    int deleteLiuyan(int id);
}
