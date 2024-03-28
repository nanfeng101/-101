package com.example.demo.enity;

import lombok.Data;

@Data
public class User_attention {
    private int id;
    private int user_id;
    private int attention_id;
    private String username;
    private String pic;
    private String introduce;
}
