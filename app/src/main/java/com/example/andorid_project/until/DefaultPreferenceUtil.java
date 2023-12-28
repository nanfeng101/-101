package com.example.andorid_project.until;

import static androidx.appcompat.widget.ResourceManagerInternal.get;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
//系统自带的偏好设置框架,不安全，用户拿到保存的文件可直接观看其内容
public class DefaultPreferenceUtil {
    //偏好设置文件名称
    private static final String TERMS_SERVICE = "TERMS_SERVICE";

    private static DefaultPreferenceUtil instance;
    private Context context;
    private SharedPreferences preferences;

    public DefaultPreferenceUtil(Context context){
        this.context=context.getApplicationContext();
        preferences = PreferenceManager.getDefaultSharedPreferences(this.context);
    }

    //用来设置用户同意了协议，下次再打开软件就不会显示协议
    public void setAcceptUserAgreement(){
        //使用系统的偏好设置来保存少量数据
        preferences.edit().putBoolean(TERMS_SERVICE,true).apply();
    }

    public Boolean isAcceptUserAgreement(){
        return preferences.getBoolean(TERMS_SERVICE,false);
    }

    //单例模式
    public static synchronized DefaultPreferenceUtil getInstance(Context context){
        if(instance==null){
            instance = new DefaultPreferenceUtil(context);
        }
        return instance;

    }
}
