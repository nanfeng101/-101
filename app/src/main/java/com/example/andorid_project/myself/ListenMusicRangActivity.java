package com.example.andorid_project.myself;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andorid_project.Activity.BaseViewActivity;
import com.example.andorid_project.R;
import com.example.andorid_project.myself.adapater.Music_ListenAdapater;
import com.example.andorid_project.myself.adapater.MyselfGedanAdapater;
import com.example.andorid_project.shouye.ShouyeActivity;

public class ListenMusicRangActivity extends BaseViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen_music_rang);
    }

    @Override
    protected void initDatum() {
        super.initDatum();
        TextView music_listen_sum = findViewById(R.id.music_listen_sum);
        String str = "("+ShouyeActivity.music_listen_list.size()+"首"+")";
        music_listen_sum.setText(str);
        Music_ListenAdapater myselfGedanAdapater = new Music_ListenAdapater(ListenMusicRangActivity.this);
        myselfGedanAdapater.setData(ShouyeActivity.music_listen_list);
        RecyclerView recyclerView = findViewById(R.id.music_listen_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListenMusicRangActivity.this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器到控件
        recyclerView.setAdapter(myselfGedanAdapater);
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        ImageView music_listen_fanhui = findViewById(R.id.music_listen_fanhui);
        music_listen_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}