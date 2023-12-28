package com.example.andorid_project.shouye.song_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.andorid_project.Activity.BaseViewActivity;
import com.example.andorid_project.Guide.GudieAdapter;
import com.example.andorid_project.R;
import com.example.andorid_project.config.Quanju;
import com.example.andorid_project.dao.shouye.MusicDao;
import com.example.andorid_project.dao.shouye.Song_listDao;
import com.example.andorid_project.shouye.enity.Bangdan;
import com.example.andorid_project.shouye.enity.Song;
import com.example.andorid_project.shouye.geci.LrcBean;
import com.example.andorid_project.shouye.geci.PraseLrc;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Song_listActivity extends BaseViewActivity {
    private Handler handler = new Handler(Looper.getMainLooper());
    private List<Bangdan> songList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);
    }

    @Override
    protected void initDatum() {
        super.initDatum();
        String id = getIntent().getStringExtra("id");
        int i = Integer.parseInt(id)-1;
        ImageView song_list_image = findViewById(R.id.song_list_image);
        Glide.with(this).load(Quanju.url1+ MusicDao.songLists.get(i).getPic()).into(song_list_image);
        TextView song_list_theme = findViewById(R.id.song_list_theme);
        song_list_theme.setText(MusicDao.songLists.get(i).getTitle());
        Song_listDao song_listDao = new Song_listDao();
        song_listDao.getSong_listMusic(id);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Bangdan song = new Bangdan();
                List<Bangdan> list1 = new ArrayList<>();
                Gson gson = new Gson();
                try {
                    JSONArray jsonArray = new JSONArray(Song_listDao.data);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        MediaPlayer mediaPlayer = new MediaPlayer();
                        JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                        song=gson.fromJson(String.valueOf(jsonObject1),Bangdan.class);
                        if(song.getLyric()!=null){
                            List<LrcBean> lrcBean = PraseLrc.jiexi(song.getLyric());
                            song.setLrcBeanList(lrcBean);
                        }
                        list1.add(song);
                    }
                    TextView song_list_musicSum = findViewById(R.id.song_list_music_sum);
                    String sum = "共"+list1.size()+"首";
                    song_list_musicSum.setText(sum);
                    Song_listAdapater song_listAdapater = new Song_listAdapater(Song_listActivity.this);
                    song_listAdapater.setData(list1);
                    RecyclerView recyclerView = findViewById(R.id.recyclerView);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Song_listActivity.this,LinearLayoutManager.VERTICAL,false);

                    recyclerView.setLayoutManager(linearLayoutManager);
                    //设置适配器到控件
                    recyclerView.setAdapter(song_listAdapater);
                    songList = list1;
//                    JSONObject jsonObject1 = new JSONObject(str);
//                    song=gson.fromJson(String.valueOf(jsonObject1),Song.class);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        },1000);
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        ImageView fanhui = findViewById(R.id.song_list_fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}