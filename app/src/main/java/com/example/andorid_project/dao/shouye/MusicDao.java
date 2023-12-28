package com.example.andorid_project.dao.shouye;

import static android.content.ContentValues.TAG;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.andorid_project.Fragement.MajorFragment;
import com.example.andorid_project.R;
import com.example.andorid_project.config.Quanju;
import com.example.andorid_project.shouye.enity.Bangdan;
import com.example.andorid_project.shouye.enity.Song;
import com.example.andorid_project.shouye.enity.Song_list;
import com.example.andorid_project.shouye.geci.LrcBean;
import com.example.andorid_project.shouye.geci.PraseLrc;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MusicDao {
    public static List<Song_list> songLists;
    public static List<Bangdan> bangdans;
    public static String data;
    public void acquireSong_list(String size){
        OkHttpClient httpClient = new OkHttpClient();
//        Map<String,String> map = new HashMap<>();
//        map.put("id","3");
//        JSONObject jsonObject = new JSONObject(map);
//        String id="3";
        FormBody formBody = new FormBody.Builder().add("currentPage","1").add("pageSize",size).build();
        String url=Quanju.url1+"getAndroidSong_listPage";
        Request request = new Request.Builder().url(url).post(formBody).build();
        Call call=httpClient.newCall(request);
//        final Map<String,String> map = new HashMap<>();
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG, "onFailure: 服务器错误");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String str = response.body().string();
//                map.put("data",str);
                Song_list song_list = new Song_list();
                List<Song_list> songList1 = new ArrayList<>();
                Gson gson = new Gson();
                try {
                    JSONArray jsonArray = new JSONArray(str);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                        song_list=gson.fromJson(String.valueOf(jsonObject1),Song_list.class);
                        songList1.add(song_list);
                    }
                    songLists=songList1;
//                    JSONObject jsonObject1 = new JSONObject(str);
//                    song=gson.fromJson(String.valueOf(jsonObject1),Song.class);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }
    public void getSongBangdan(){
        OkHttpClient httpClient = new OkHttpClient();
        FormBody formBody = new FormBody.Builder().add("bangname","抖音热歌榜").build();
        String url=Quanju.url1+"getAndroidBangSong";
        final Request request = new Request.Builder().url(url).post(formBody).build();
        Call call=httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG, "onFailure: 服务器错误");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String str = response.body().string();
                data=str;
                Bangdan bangdan = new Bangdan();
                List<Bangdan> list = new ArrayList<>();
                Gson gson = new Gson();
                try {
                    JSONArray jsonArray = new JSONArray(str);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                        bangdan=gson.fromJson(String.valueOf(jsonObject1),Bangdan.class);
                        if(bangdan.getLyric()!=null){
                            List<LrcBean> lrcBean = PraseLrc.jiexi(bangdan.getLyric());
                            bangdan.setLrcBeanList(lrcBean);
                        }
                        list.add(bangdan);
                    }
                    bangdans=list;
//                    JSONObject jsonObject1 = new JSONObject(str);
//                    song=gson.fromJson(String.valueOf(jsonObject1),Song.class);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
