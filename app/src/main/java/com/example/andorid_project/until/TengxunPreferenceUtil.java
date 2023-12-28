package com.example.andorid_project.until;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.tencent.mmkv.MMKV;

//腾讯偏好设置
public class TengxunPreferenceUtil {
    private static final String SHOW_SERVICE = "SHOW_SERVICE";
    private static final String Music_play_SERVICE = "Music_play_SERVICE";
    private static final String plat_Geci_SERVICE = "plat_Geci_SERVICE";
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
}
