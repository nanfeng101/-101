package com.example.andorid_project.myself;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.andorid_project.Activity.BaseViewActivity;
import com.example.andorid_project.R;
import com.example.andorid_project.config.Quanju;
import com.example.andorid_project.dao.shouye.MusicDao;
import com.example.andorid_project.shouye.ShouyeActivity;
import com.example.andorid_project.shouye.enity.Bangdan;
import com.example.andorid_project.shouye.geci.LrcBean;
import com.example.andorid_project.shouye.geci.PraseLrc;
import com.example.andorid_project.until.TengxunPreferenceUtil;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyHomeActivity extends BaseViewActivity {
    private Handler handler = new Handler(Looper.getMainLooper());
    private TengxunPreferenceUtil tengxunPreferenceUtil = new TengxunPreferenceUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_home);
    }

    @Override
    protected void initDatum() {
        super.initDatum();
        if(tengxunPreferenceUtil.isUserLogin()){
            TextView myhome_username = findViewById(R.id.myhome_username);
            ImageView myhome_pic = findViewById(R.id.myhome_pic);
            myhome_pic.setImageResource(tengxunPreferenceUtil.getUserPic());
            myhome_username.setText(tengxunPreferenceUtil.getUserName());
            List<Bangdan> list = ShouyeActivity.music_listen_list;

            TextView jinpai_biaoti = findViewById(R.id.myhome_jinpai_music_name);
            TextView jinpai_singer = findViewById(R.id.myhome_jinpai_music_singer);
            ImageView jinpai_pic = findViewById(R.id.myhome_jinpai_music_pic);
            jinpai_biaoti.setText(list.get(0).getName());
            jinpai_singer.setText(list.get(0).getSinger_name());
            Glide.with(MyHomeActivity.this).load(Quanju.url1+list.get(0).getPic()).into(jinpai_pic);
            TextView yinpai_biaoti = findViewById(R.id.myhome_yinpai_music_name);
            TextView yinpai_singer = findViewById(R.id.myhome_yinpai_music_singer);
            ImageView yinpai_pic = findViewById(R.id.myhome_yinpai_music_pic);
            yinpai_biaoti.setText(list.get(1).getName());
            yinpai_singer.setText(list.get(1).getSinger_name());
            Glide.with(MyHomeActivity.this).load(Quanju.url1+list.get(1).getPic()).into(yinpai_pic);
            TextView tongpai_name = findViewById(R.id.myhome_tongpai_music_name);
            TextView tongpai_singer = findViewById(R.id.myhome_tongpai_music_singer);
            ImageView tongpai_pic = findViewById(R.id.myhome_tongpai_music_pic);
            tongpai_name.setText(list.get(2).getName());
            tongpai_singer.setText(list.get(2).getSinger_name());
            Glide.with(MyHomeActivity.this).load(Quanju.url1+list.get(2).getPic()).into(tongpai_pic);
        }
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        ImageView myhoume_fanhui=findViewById(R.id.myhome_fanhui);
        myhoume_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}