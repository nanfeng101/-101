package com.example.demo.enity;

import lombok.Data;

import java.util.Date;
@Data
public class User {
    private int id;
    private String pic;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Date create_time;
    private String introduce;
    private String sex;
    private String location;
    private Date birth;
    private String birth1;
    private String create_time1;
    private String oldphone;
    private String yanzheng;
    private String oldemail;
    private boolean disable;
}
