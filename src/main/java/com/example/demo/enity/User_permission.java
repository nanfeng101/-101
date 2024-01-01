package com.example.demo.enity;

public class User_permission {
    private int id;
    private int user_id;
    private boolean islogin;
    private boolean text_limit;
    private boolean user_limit;
    private boolean shouye_limit;
    private boolean iform_limit;
    private boolean examine_limit;
    private boolean limit;
    private boolean liuyan_limit;
    private boolean link_limit;

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

    public boolean isIslogin() {
        return islogin;
    }

    public void setIslogin(boolean islogin) {
        this.islogin = islogin;
    }

    public boolean isText_limit() {
        return text_limit;
    }

    public void setText_limit(boolean text_limit) {
        this.text_limit = text_limit;
    }

    public boolean isUser_limit() {
        return user_limit;
    }

    public void setUser_limit(boolean user_limit) {
        this.user_limit = user_limit;
    }

    public boolean isShouye_limit() {
        return shouye_limit;
    }

    public void setShouye_limit(boolean shouye_limit) {
        this.shouye_limit = shouye_limit;
    }

    public boolean isIform_limit() {
        return iform_limit;
    }

    public void setIform_limit(boolean iform_limit) {
        this.iform_limit = iform_limit;
    }

    public boolean isExamine_limit() {
        return examine_limit;
    }

    public void setExamine_limit(boolean examine_limit) {
        this.examine_limit = examine_limit;
    }

    public boolean isLimit() {
        return limit;
    }

    public void setLimit(boolean limit) {
        this.limit = limit;
    }

    public boolean isLiuyan_limit() {
        return liuyan_limit;
    }

    public void setLiuyan_limit(boolean liuyan_limit) {
        this.liuyan_limit = liuyan_limit;
    }

    public boolean isLink_limit() {
        return link_limit;
    }

    public void setLink_limit(boolean link_limit) {
        this.link_limit = link_limit;
    }
}
