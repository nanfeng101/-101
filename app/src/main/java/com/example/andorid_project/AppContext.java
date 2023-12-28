package com.example.andorid_project;


import android.app.Application;
import android.util.Log;
import static android.content.ContentValues.TAG;
import com.tencent.mmkv.MMKV;
//该界面只在app启动的时候执行一次
public class AppContext extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initMMKV();

    }
    //初始化 腾讯开源的高性能keyValue存储
    private void initMMKV(){
        String rootDir = MMKV.initialize(this);
        String msg="initMMKV: "+rootDir;
        Log.d(TAG, msg);
    }
}
