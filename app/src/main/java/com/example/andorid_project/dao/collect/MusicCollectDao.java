package com.example.andorid_project.dao.collect;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.example.andorid_project.config.Quanju;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MusicCollectDao {
    public String data;
    public void getAndroidCollectMusic(String id){
        OkHttpClient httpClient = new OkHttpClient();
        FormBody formBody = new FormBody.Builder().add("phone",id).build();
        String url= Quanju.url1+"getAndroidUserCollectMusic";
        Request request = new Request.Builder().url(url).post(formBody).build();
        Call call=httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG, "onFailure: 服务器错误");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                data = response.body().string();
                System.out.println(data);
            }
        });
    }

}
