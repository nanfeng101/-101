package com.example.demo.enity;

import lombok.Data;

@Data
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
}
