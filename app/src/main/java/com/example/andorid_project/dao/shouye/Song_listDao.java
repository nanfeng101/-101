package com.example.andorid_project.dao.shouye;

import static android.content.ContentValues.TAG;

import android.media.MediaPlayer;
import android.util.Log;

import com.example.andorid_project.config.Quanju;
import com.example.andorid_project.shouye.enity.Bangdan;
import com.example.andorid_project.shouye.enity.Song;
import com.example.andorid_project.shouye.geci.LrcBean;
import com.example.andorid_project.shouye.geci.PraseLrc;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Song_listDao {
    public static String data;
    public void getSong_listMusic(String id){
        OkHttpClient httpClient = new OkHttpClient();
        FormBody formBody = new FormBody.Builder().add("id",id).build();
        String url= Quanju.url1+"getAndroidSong_listMusic";
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
            }
        });
    }
}
