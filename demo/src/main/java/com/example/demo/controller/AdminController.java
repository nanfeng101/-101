package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
public class AdminController {
    @Resource
    private AdminService adminService;
    @PostMapping("/login")
    public String login(@RequestBody UserEntity user, HttpSession httpSession){
        return adminService.login(user,httpSession);
    }
}
