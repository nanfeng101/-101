package com.example.demo.enity;

import lombok.Data;

import java.util.Date;
@Data
public class User_search_history {
    private int id;
    private int user_id;
    private String content;
    private Date time;
}
