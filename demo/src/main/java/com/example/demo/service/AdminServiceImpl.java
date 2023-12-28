package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.AdminMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
@Service
public class AdminServiceImpl implements AdminService{
    @Resource
    private AdminMapper adminMapper;
    public String login(UserEntity user, HttpSession httpSession){
        UserEntity user1 = adminMapper.login(user);
        if(user1!=null){
            httpSession.setAttribute("auser",user1);
            return "ok";
        }
        else{
            return "no";
        }
    }
}
