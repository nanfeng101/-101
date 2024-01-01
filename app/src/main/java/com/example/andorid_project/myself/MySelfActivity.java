package com.example.andorid_project.myself;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.example.andorid_project.Activity.BaseViewActivity;
import com.example.andorid_project.R;
import com.example.andorid_project.dao.collect.Song_list_CollectDao;
import com.example.andorid_project.dao.shouye.MusicDao;
import com.example.andorid_project.dao.shouye.Song_listDao;
import com.example.andorid_project.myself.adapater.MyselfGedanAdapater;
import com.example.andorid_project.shouye.apapter.GedanAdapater;
import com.example.andorid_project.shouye.enity.Bangdan;
import com.example.andorid_project.shouye.enity.Song_list;
import com.example.andorid_project.shouye.geci.LrcBean;
import com.example.andorid_project.shouye.geci.PraseLrc;
import com.example.andorid_project.shouye.song_list.Song_listActivity;
import com.example.andorid_project.shouye.song_list.Song_listAdapater;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MySelfActivity extends BaseViewActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_self);
    }


}