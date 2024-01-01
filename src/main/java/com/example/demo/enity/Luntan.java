package com.example.demo.enity;

import java.util.Date;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBiaoti() {
        return biaoti;
    }

    public void setBiaoti(String biaoti) {
        this.biaoti = biaoti;
    }

    public int getView_sum() {
        return view_sum;
    }

    public void setView_sum(int view_sum) {
        this.view_sum = view_sum;
    }

    public boolean isIs_cover() {
        return is_cover;
    }

    public void setIs_cover(boolean is_cover) {
        this.is_cover = is_cover;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getIscover1() {
        return iscover1;
    }

    public void setIscover1(Integer iscover1) {
        this.iscover1 = iscover1;
    }
}
