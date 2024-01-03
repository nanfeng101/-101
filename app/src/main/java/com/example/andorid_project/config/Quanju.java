package com.example.andorid_project.config;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Message;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.andorid_project.R;
import com.example.andorid_project.dao.shouye.MusicDao;
import com.example.andorid_project.shouye.ShouyeActivity;
import com.example.andorid_project.shouye.enity.Bangdan;
import com.example.andorid_project.shouye.geci.geciActivity;
import com.example.andorid_project.until.TengxunPreferenceUtil;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Quanju {
    public static String url1="http://192.168.130.155:8081/zf/";

    public static void PlayMusic(Context context1, Bangdan item,int id){
        if(geciActivity.timer!=null){
            geciActivity.timer.cancel();
        }
        TengxunPreferenceUtil tengxunPreferenceUtil = new TengxunPreferenceUtil();
        try{
            ShouyeActivity.mediaPlayer.release();
            MediaPlayer mediaPlayer1 = new MediaPlayer();
            mediaPlayer1.setDataSource(Quanju.url1+item.getUrl());
            mediaPlayer1.prepare();
            ShouyeActivity.mediaPlayer=mediaPlayer1;
            ShouyeActivity.mediaPlayer.start();
        }catch (IOException e){
            e.printStackTrace();
        }
        ShouyeActivity.shouye_zhuzhe.setText(item.getName());
        Glide.with(context1).load(url1+item.getPic()).into(ShouyeActivity.qia);
        ShouyeActivity.music_play.setImageResource(R.drawable.pause);
        ShouyeActivity.musicId=id;
        if(geciActivity.geci_biaoti!=null) {
            geciActivity.geci_play.setImageResource(R.drawable.pause_white);
            geciActivity.geci_biaoti.setText(item.getName());
            geciActivity.geci_zhuozhe.setText(item.getSinger_name());
            Glide.with(context1).load(url1+item.getPic()).into(geciActivity.geci_music_pic);
            geciActivity.lrcBeanList = item.getLrcBeanList();
        }
        tengxunPreferenceUtil.setPlayMusic(false);
        if(tengxunPreferenceUtil.isPlayGEci()) {
            Timer timer1 = new Timer();
            geciActivity.timer = timer1;
            geciActivity.timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    int time;
                    try {
                        time = ShouyeActivity.mediaPlayer.getCurrentPosition();
                    } catch (Exception e) {
                        e.printStackTrace();
                        time = 0;
                    }
                    Message message = Message.obtain();
                    message.arg1 = time;
                    geciActivity.handler.sendMessage(message);
                }
            }, 500, 1000);
        }
        MusicDao musicDao1 = new MusicDao();
        musicDao1.add_Musci_listen_sum(tengxunPreferenceUtil.getUserPhone(),Integer.toString(ShouyeActivity.bangdanList.get(ShouyeActivity.musicId).getId()));
    }
}
