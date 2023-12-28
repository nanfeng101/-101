package com.example.demo.entity;

import java.util.Date;

public class Singer {
    private int id;
    private String name;
    private int sex;
    private String pic;
    private Date birth;
    private String birth1;
    private String location;
    private String introduction;
    private int attention_sum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getBirth1() {
        return birth1;
    }

    public void setBirth1(String birth1) {
        this.birth1 = birth1;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getAttention_sum() {
        return attention_sum;
    }

    public void setAttention_sum(int attention_sum) {
        this.attention_sum = attention_sum;
    }
}
