package com.example.andorid_project.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    /**找控件
     *
     */
    protected void initViews(){};

    /**
     * 设置数据
     */
    protected void initDatum(){};

    /**
     * 设置监听器
     */
    protected void initListeners(){};

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initDatum();
        initViews();
        initListeners();
    }
}
