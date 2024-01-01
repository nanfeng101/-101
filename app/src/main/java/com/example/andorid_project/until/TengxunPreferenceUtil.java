package com.example.andorid_project.until;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.andorid_project.R;
import com.tencent.mmkv.MMKV;

//腾讯偏好设置
public class TengxunPreferenceUtil {
    private static final String SHOW_SERVICE = "SHOW_SERVICE";
    private static final String Music_play_SERVICE = "Music_play_SERVICE";
    private static final String plat_Geci_SERVICE = "plat_Geci_SERVICE";
    private static final String USER_LOGIN_SERVICE = "USER_LOGIN_SERVICE";
    private static final String USER_NAME_SERVICE = "USER_NAME_SERVICE";
    private static final String USER_PIC_SERVICE = "USER_PIC_SERVICE";
    private static final String FIRST_LOGIN_SERVICE = "FIRST_LOGIN_SERVICE";
    private static final String USER_PHONE_SERVICE = "USER_PHONE_SERVICE";
    private MMKV kv=MMKV.defaultMMKV();

    //用来设置用户同意了协议，下次再打开软件就不会显示协议
    public void setAcceptUserAgreement(){
        //使用系统的偏好设置来保存少量数据
//        kv.edit().putBoolean(SHOW_SERVICE,true).apply();
        kv.encode(SHOW_SERVICE, true);
    }

    public Boolean isAcceptUserAgreement(){
//        return kv.getBoolean(SHOW_SERVICE,false);
        return kv.decodeBool(SHOW_SERVICE);
    }
    public void setPlayMusic(boolean id){
//        kv.edit().putBoolean(SHOW_SERVICE,true).apply();
        kv.edit().putBoolean(Music_play_SERVICE,id).apply();
    }

    public Boolean isPlayMusic(){
        return kv.getBoolean(Music_play_SERVICE,false);
    }
    public  void setPlayGeci(boolean id){
//        kv.edit().putBoolean(SHOW_SERVICE,true).apply();
        kv.edit().putBoolean(plat_Geci_SERVICE,id).apply();
    }

    public Boolean isPlayGEci(){
        return kv.getBoolean(plat_Geci_SERVICE,false);
    }

    //判断用户是否登录
    public  void setUserLogin(boolean id){
        kv.edit().putBoolean(USER_LOGIN_SERVICE,id).apply();
    }

    public Boolean isUserLogin(){
        return kv.getBoolean(USER_LOGIN_SERVICE,false);
    }
    //存储用户的昵称
    public void setUsername(String str){
        kv.edit().putString(USER_NAME_SERVICE,str);
    }
    public String getUserName(){
        return kv.getString(USER_NAME_SERVICE,"用户登录");
    }
    //存储用户的头像
    public void setUserPic(int pic){
        kv.edit().putInt(USER_PIC_SERVICE,pic);
    }
    public int getUserPic(){
        return kv.getInt(USER_PIC_SERVICE, R.drawable.wei_login);
    }
    //用于判断用户是否注册过
    public void setFirstLogin(boolean id){
        kv.edit().putBoolean(FIRST_LOGIN_SERVICE,id).apply();
    }
    public boolean isFirstLogin(){
        return kv.getBoolean(FIRST_LOGIN_SERVICE,false);
    }
    //用于存储用户账号
    public void setUserPhone(String str){
        kv.edit().putString(USER_PHONE_SERVICE,str);
    }
    public String getUserPhone(){
        return kv.getString(USER_PHONE_SERVICE,"用户登录");
    }


}
