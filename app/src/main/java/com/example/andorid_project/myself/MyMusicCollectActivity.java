package com.example.andorid_project.myself;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andorid_project.Activity.BaseViewActivity;
import com.example.andorid_project.R;
import com.example.andorid_project.shouye.ShouyeActivity;
import com.example.andorid_project.shouye.song_list.Song_listActivity;
import com.example.andorid_project.shouye.song_list.Song_listAdapater;

public class MyMusicCollectActivity extends BaseViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_music_collect);
    }

    @Override
    protected void initDatum() {
        super.initDatum();
        TextView mycollect_music_sum = findViewById(R.id.my_collect_music_sum);
        String sum = "共"+Integer.toString(ShouyeActivity.collectList.size())+"首";
        mycollect_music_sum.setText(sum);
        Song_listAdapater song_listAdapater = new Song_listAdapater(MyMusicCollectActivity.this);
        song_listAdapater.setData(ShouyeActivity.collectList);
        RecyclerView recyclerView = findViewById(R.id.mycollect_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyMusicCollectActivity.this,LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器到控件
        recyclerView.setAdapter(song_listAdapater);
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        ImageView music_ganhui = findViewById(R.id.mycollect_fanhui);
        music_ganhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}