package com.example.demo.service;

import com.example.demo.entity.UserEntity;

import javax.servlet.http.HttpSession;

public interface AdminService {
    public String login(UserEntity user, HttpSession httpSession);
}
