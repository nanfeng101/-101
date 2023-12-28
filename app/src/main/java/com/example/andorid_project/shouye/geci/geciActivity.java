package com.example.andorid_project.shouye.geci;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.andorid_project.Activity.BaseViewActivity;
import com.example.andorid_project.R;
import com.example.andorid_project.config.Quanju;
import com.example.andorid_project.shouye.Fragement.MusicListFragement;
import com.example.andorid_project.shouye.ShouyeActivity;
import com.example.andorid_project.until.TengxunPreferenceUtil;

import org.w3c.dom.Text;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class geciActivity extends BaseViewActivity {
    private TengxunPreferenceUtil tengxunPreferenceUtil = new TengxunPreferenceUtil();
    private static TextView geci_first;
    private static TextView geci_second;
    private static TextView geci_time_change;
    private static TextView geci_time_sum;
    public static List<LrcBean> lrcBeanList;
    private static SeekBar seekBar;
    public static TextView geci_zhuozhe;
    public static TextView geci_biaoti;
    public static ImageView geci_play;
    public static Timer timer;
    public static ImageView geci_music_pic;
    public static Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if(seekBar!=null) {
                seekBar.setProgress(msg.arg1 / 1000);
            }
            if(msg.arg1<1000){
                System.out.println("更新时间");
                geci_time_sum.setText(new SimpleDateFormat("mm:ss", Locale.getDefault()).format(new Date(ShouyeActivity.mediaPlayer.getDuration())));
            }
            geci_time_change.setText(new SimpleDateFormat("mm:ss", Locale.getDefault()).format(new Date(msg.arg1)));
            for(int i=0;i<lrcBeanList.size();i++){
                if(msg.arg1>=lrcBeanList.get(i).getLongtime() && i<lrcBeanList.size()-1){
                    geci_first.setText(lrcBeanList.get(i).getText());
                    geci_second.setText(lrcBeanList.get(i+1).getText());
                    lrcBeanList.get(i).setLongtime(1000000);
                    break;
                }
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geci);
    }

    @Override
    protected void initDatum() {
        super.initDatum();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        geci_play = findViewById(R.id.geci_play);
        geci_biaoti=findViewById(R.id.geci_biaoti);
        geci_zhuozhe=findViewById(R.id.geci_zhuozhe);
        geci_music_pic=findViewById(R.id.geci_music_pic);
        geci_biaoti.setText(getIntent().getStringExtra("biaoti"));
        geci_zhuozhe.setText(getIntent().getStringExtra("zhuozhe"));
        seekBar=findViewById(R.id.seekbar);
        seekBar.setMax(ShouyeActivity.mediaPlayer.getDuration()/1000);
        seekBar.setProgress(0);
        tengxunPreferenceUtil.setPlayGeci(true);
        if(tengxunPreferenceUtil.isPlayMusic()){
            geci_play.setImageResource(R.drawable.play_white);
            ShouyeActivity.music_play.setImageResource(R.drawable.pause);
        }else{
            geci_play.setImageResource(R.drawable.pause_white);
            ShouyeActivity.music_play.setImageResource(R.drawable.play);
        }

    }
    @Override
    protected void initListeners() {
        super.initListeners();
        lrcBeanList = ShouyeActivity.bangdanList.get(ShouyeActivity.musicId).getLrcBeanList();
        geci_time_sum = findViewById(R.id.geci_time_sum);
        geci_time_change = findViewById(R.id.geci_time_change);
        geci_time_sum.setText(new SimpleDateFormat("mm:ss", Locale.getDefault()).format(new Date(ShouyeActivity.mediaPlayer.getDuration())));
        geci_first=findViewById(R.id.geci_first);
        geci_second=findViewById(R.id.geci_second);
        if(lrcBeanList.size()!=0) {
            geci_first.setText(lrcBeanList.get(0).getText());
            geci_second.setText(lrcBeanList.get(1).getText());
        }else{
            geci_first.setText("歌词暂无");
            geci_second.setText("歌词暂无");
        }
        if(timer==null) {
            Timer timer1 = new Timer();
            timer = timer1;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    int time = ShouyeActivity.mediaPlayer.getCurrentPosition();
                    Message message = Message.obtain();
                    message.arg1 = time;
                    geciActivity.handler.sendMessage(message);

                }
            }, 200, 1000);
        }
//        int max = ShouyeActivity.mediaPlayer.getDuration();
//        ProgressBar progressBar = findViewById(R.id.progressBar);
//        progressBar.setMax(max);
        ImageView fanhui = findViewById(R.id.geci_fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        geci_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tengxunPreferenceUtil.isPlayMusic()){
                    ShouyeActivity.mediaPlayer.start();
                    geci_play.setImageResource(R.drawable.pause_white);
                    tengxunPreferenceUtil.setPlayMusic(false);
                }
                else{
                    ShouyeActivity.mediaPlayer.pause();
                    geci_play.setImageResource(R.drawable.play_white);
                    tengxunPreferenceUtil.setPlayMusic(true);
                }
            }
        });
        //进度条监听事件
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
//                ShouyeActivity.mediaPlayer.pause();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int time=seekBar.getProgress()*1000;
                for(int i=0;i<lrcBeanList.size();i++){
                    if(time<=lrcBeanList.get(i).getLongtime()){
                        lrcBeanList.get(i).setLongtime(1000000);
                    }
                }
                ShouyeActivity.mediaPlayer.seekTo(seekBar.getProgress()*1000);
                ShouyeActivity.mediaPlayer.start();
                geci_play.setImageResource(R.drawable.pause_white);
                tengxunPreferenceUtil.setPlayMusic(false);
            }
        });
        //歌词页面上一首监听事件
        ImageView geci_previous = findViewById(R.id.geco_perious_play);
        geci_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: 上一首歌曲");
                ShouyeActivity.mediaPlayer.release();
                if(ShouyeActivity.musicId<1){
                    ShouyeActivity.musicId=ShouyeActivity.bangdanList.size();
                }
                ShouyeActivity.musicId--;
                timer.cancel();
                try {
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(Quanju.url1+ShouyeActivity.bangdanList.get(ShouyeActivity.musicId).getUrl());
                    mediaPlayer.prepare();
                    ShouyeActivity.mediaPlayer = mediaPlayer;
                    ShouyeActivity.mediaPlayer.start();
                }catch (IOException e){
                    Log.d(TAG, ShouyeActivity.bangdanList.get(ShouyeActivity.musicId).getName());
                }
                Timer timer1 = new Timer();
                timer=timer1;
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        int time;
                        try {
                            time = ShouyeActivity.mediaPlayer.getCurrentPosition();
                        }catch (Exception e){
                            e.printStackTrace();
                            time=0;
                        }
                        Message message = Message.obtain();
                        message.arg1 = time;
                        geciActivity.handler.sendMessage(message);

                    }
                },500,1000);
                Glide.with(geciActivity.this).load(Quanju.url1+ShouyeActivity.bangdanList.get(ShouyeActivity.musicId).getPic()).into(geci_music_pic);
                geci_biaoti.setText(ShouyeActivity.bangdanList.get(ShouyeActivity.musicId).getName());
//                ShouyeActivity.qia.setImageResource(ShouyeActivity.musicXinxi.get(ShouyeActivity.musicId).getPic());
                geci_zhuozhe.setText(ShouyeActivity.bangdanList.get(ShouyeActivity.musicId).getSinger_name());
                ShouyeActivity.shouye_zhuzhe.setText(ShouyeActivity.bangdanList.get(ShouyeActivity.musicId).getName());
                lrcBeanList = ShouyeActivity.bangdanList.get(ShouyeActivity.musicId).getLrcBeanList();
                geci_first.setText(lrcBeanList.get(0).getText());
                geci_second.setText(lrcBeanList.get(1).getText());
                seekBar.setMax(ShouyeActivity.mediaPlayer.getDuration()/1000);
                geci_play.setImageResource(R.drawable.pause_white);
            }
        });
        //歌词页面下一首监听事件
        ImageView geci_next_music = findViewById(R.id.geci_next_music);
        geci_next_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: 下一首歌曲");
                ShouyeActivity.mediaPlayer.release();
                if(ShouyeActivity.musicId>=ShouyeActivity.bangdanList.size()-1){
                    ShouyeActivity.musicId=-1;
                }
                ShouyeActivity.musicId++;
                timer.cancel();
                try {
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(Quanju.url1+ShouyeActivity.bangdanList.get(ShouyeActivity.musicId).getUrl());
                    mediaPlayer.prepare();
                    ShouyeActivity.mediaPlayer = mediaPlayer;
                    ShouyeActivity.mediaPlayer.start();
                }catch (Exception e){
                    Log.d(TAG, ShouyeActivity.bangdanList.get(ShouyeActivity.musicId).getName());
                }
                Timer timer1 = new Timer();
                timer=timer1;
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        int time;
                        try {
                            time = ShouyeActivity.mediaPlayer.getCurrentPosition();
                        }catch (Exception e){
                            e.printStackTrace();
                            time=0;
                        }
                        Message message = Message.obtain();
                        message.arg1 = time;
                        geciActivity.handler.sendMessage(message);

                    }
                },200,1000);
                Glide.with(geciActivity.this).load(Quanju.url1+ShouyeActivity.bangdanList.get(ShouyeActivity.musicId).getPic()).into(geci_music_pic);
                geci_biaoti.setText(ShouyeActivity.bangdanList.get(ShouyeActivity.musicId).getName());
//                ShouyeActivity.qia.setImageResource(ShouyeActivity.musicXinxi.get(ShouyeActivity.musicId).getPic());
                geci_zhuozhe.setText(ShouyeActivity.bangdanList.get(ShouyeActivity.musicId).getSinger_name());
                ShouyeActivity.shouye_zhuzhe.setText(ShouyeActivity.bangdanList.get(ShouyeActivity.musicId).getName());
                lrcBeanList = ShouyeActivity.bangdanList.get(ShouyeActivity.musicId).getLrcBeanList();
                if(lrcBeanList.size()!=0) {
                    geci_first.setText(lrcBeanList.get(0).getText());
                    geci_second.setText(lrcBeanList.get(0).getText());
                }
                seekBar.setMax(ShouyeActivity.mediaPlayer.getDuration()/1000);
                geci_play.setImageResource(R.drawable.pause_white);
            }
        });

    }
}