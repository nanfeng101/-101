package com.example.demo.enity;

import lombok.Data;

import java.util.Date;
@Data
public class Luntan {
    private int id;
    private int user_id;
    private String content;
    private String avatar;
    private String biaoti;
    private int view_sum;
    private boolean is_cover;
    private Date create_time;
    private String time;
    private String pic;
    private String username;
    private Integer iscover1;

    public boolean isIs_cover() {
        return is_cover;
    }

    public void setIs_cover(boolean is_cover) {
        this.is_cover = is_cover;
    }
}
