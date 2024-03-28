package com.example.demo.enity;

import lombok.Data;

import java.util.Date;
@Data
public class User_chat {
    private int id;
    private int user_id;
    private int send_user_id;
    private String message;
    private int ispic;
    private Date send_time;
    private int isread;
    private String username;
    private String pic;
    private String introduce;
    private int is_send;
    private String send_time1;
    private int text_id;
}
