package com.example.demo.enity;

import lombok.Data;

@Data
public class User_chat_user {
    private int id;
    private int user_id;
    private int send_user_id;
    private String username;
    private String pic;
    private String introduce;
    private int sum;
}
