package com.example.demo.enity;

import lombok.Data;

import java.util.Date;
@Data
public class Photo {
    private int id;
    private int user_id;
    private String pic1;
    private String content;
    private String username;
    private Date time;
    private String time1;
}
