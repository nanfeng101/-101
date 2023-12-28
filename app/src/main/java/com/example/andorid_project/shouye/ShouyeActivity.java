package com.example.andorid_project.shouye;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.andorid_project.Activity.BaseViewActivity;
import com.example.andorid_project.Fragement.MajorFragment;
import com.example.andorid_project.Fragement.TabFragement;
import com.example.andorid_project.Guide.GuideActivity;
import com.example.andorid_project.LoginOrRegister.LoginActivity;
import com.example.andorid_project.MainActivity;
import com.example.andorid_project.R;
import com.example.andorid_project.config.Quanju;
import com.example.andorid_project.dao.shouye.MusicDao;
import com.example.andorid_project.shouye.Fragement.MusicListFragement;
import com.example.andorid_project.shouye.apapter.DrawerAdapater;
import com.example.andorid_project.shouye.enity.Bangdan;
import com.example.andorid_project.shouye.enity.MajorEnity;
import com.example.andorid_project.shouye.enity.ShouyeEnity;
import com.example.andorid_project.shouye.enity.Song;
import com.example.andorid_project.shouye.geci.LrcBean;
import com.example.andorid_project.shouye.geci.PraseLrc;
import com.example.andorid_project.shouye.geci.geciActivity;
import com.example.andorid_project.shouye.until.ShouyeUntil;
import com.example.andorid_project.until.TengxunPreferenceUtil;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ShouyeActivity extends BaseViewActivity {
    private Context mContext = this;
    private Handler handler = new Handler(Looper.getMainLooper());
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private String[] mTitles = {"首页", "听书", "本地", "我的"};
    private int[] mIconUnselectIds = {R.drawable.major_page,R.drawable.story
            ,R.drawable.local,R.drawable.my
            };
    private int[] mIconSelectIds = {R.drawable.major_page_onclick,R.drawable.story_onclick
            ,R.drawable.local_onclick,R.drawable.my_onclick
            };
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ViewPager mViewPager;
    private CommonTabLayout mTabLayout_2;
    public static MediaPlayer mediaPlayer;
    public static ArrayList<Integer> musiclist=new ArrayList<>();
    public static ArrayList<LrcBean> musicXinxi = new ArrayList<>();
    public static int musicId=0;
    public ImageView shouye_next_music;

    public static ImageView qia;
    public static TextView shouye_zhuzhe;
    public static List<Bangdan> bangdanList;
    public static ImageView music_play;
    public static RelativeLayout overlay;
    public Context getContext(){
        return mContext;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shouye);
    }

    @Override
    protected void initDatum() {
        super.initDatum();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        musicXinxi = CheshiData.add();
        qia = findViewById(R.id.qianwang);
        qia.setImageResource(musicXinxi.get(musicId).getPic());
        shouye_zhuzhe=findViewById(R.id.shouye_zhuozhe);
        String xinxi = musicXinxi.get(musicId).getBiaoti()+"-"+musicXinxi.get(musicId).getZhuozhe();
        shouye_zhuzhe.setText(xinxi);
        musiclist.add(R.raw.music);
        musiclist.add(R.raw.music1);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        for (String title : mTitles) {
            mFragments.add(TabFragement.getInstance(title,getSupportFragmentManager(),this));
        }
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new ShouyeEnity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        int id=0;

        View decorView2 = getWindow().getDecorView();
        mViewPager = ShouyeUntil.find(decorView2, R.id.vp_2);
        mViewPager.setOffscreenPageLimit(3);  //设置ViewPager预先加载的页数
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        /** with ViewPager */
        mTabLayout_2 = ShouyeUntil.find(decorView2, R.id.tl_2);
        mTabLayout_2.setTabData(mTabEntities);
        tl_2();
        String isLogin = getIntent().getAction();
        if(isLogin!=null && isLogin.equals("ACTION_LOGIN")){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        ImageView music_play=findViewById(R.id.play);
        TengxunPreferenceUtil tengxunPreferenceUtil = new TengxunPreferenceUtil();
        if(tengxunPreferenceUtil.isPlayMusic()){
            music_play.setImageResource(R.drawable.play);
        }else{
            music_play.setImageResource(R.drawable.pause);
        }
        MusicDao musicDao = new MusicDao();
        musicDao.getSongBangdan();

    }

    @Override
    protected void initListeners() {
        super.initListeners();
        music_play=findViewById(R.id.play);
        TengxunPreferenceUtil tengxunPreferenceUtil = new TengxunPreferenceUtil();
        tengxunPreferenceUtil.setPlayGeci(false);
        tengxunPreferenceUtil.setPlayMusic(true);
        mediaPlayer = MediaPlayer.create(this,R.raw.music);
//        try{
//            mediaPlayer.setDataSource("http://124.220.16.147:7001/zf/可能.mp3");
//            mediaPlayer.prepare();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
        music_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    mediaPlayer.setDataSource(audioFileUrl);
//                    mediaPlayer.prepare();
                    if(tengxunPreferenceUtil.isPlayMusic()){
                        tengxunPreferenceUtil.setPlayMusic(false);
                        Log.d(TAG, "onClick: play music");
                        mediaPlayer.start();
                        music_play.setImageResource(R.drawable.pause);
                        geciActivity.geci_play.setImageResource(R.drawable.pause_white);
                    }else{
                        tengxunPreferenceUtil.setPlayMusic(true);
                        mediaPlayer.pause();
                        Log.d(TAG, "onClick: play stop");
//                        mediaPlayer[0].release();
//                        mediaPlayer[0] = MediaPlayer.create(ShouyeActivity.this,R.raw.music);
                        music_play.setImageResource(R.drawable.play);
                        geciActivity.geci_play.setImageResource(R.drawable.play_white);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        ImageView qianwang = findViewById(R.id.qianwang);
        qianwang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShouyeActivity.this, geciActivity.class);
                intent.putExtra("biaoti",bangdanList.get(musicId).getName());
                intent.putExtra("zhuozhe",bangdanList.get(musicId).getSinger_name());
                startActivity(intent);
            }
        });
        //歌曲播放结束监听事件
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.release();
                if(musicId>=bangdanList.size()-1){
                    musicId=0;
                }
                musicId++;
                try {
                    if(geciActivity.timer!=null) {
                        geciActivity.timer.cancel();
                    }
                    MediaPlayer mediaPlayer1 = new MediaPlayer();
                    mediaPlayer.setDataSource(Quanju.url1+bangdanList.get(musicId).getUrl());
                    mediaPlayer.prepare();
                    mediaPlayer = mediaPlayer1;
                    mediaPlayer.start();
                    if(tengxunPreferenceUtil.isPlayGEci()) {
                        Timer timer1 = new Timer();
                        geciActivity.timer = timer1;
                        geciActivity.timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                int time = ShouyeActivity.mediaPlayer.getCurrentPosition();
                                Message message = Message.obtain();
                                message.arg1 = time;
                                geciActivity.handler.sendMessage(message);
                            }
                        }, 500, 1000);
                    }
                }catch(Exception e){
                    Log.d(TAG, bangdanList.get(musicId).getName());
                }
//                qia.setImageResource(musicXinxi.get(musicId).getPic());
//                String xinxi = musicXinxi.get(musicId).getBiaoti()+"-"+musicXinxi.get(musicId).getZhuozhe();
//                shouye_zhuzhe.setText(xinxi);
                Glide.with(ShouyeActivity.this).load(Quanju.url1+bangdanList.get(musicId).getPic()).into(qia);
                shouye_zhuzhe.setText(bangdanList.get(musicId).getName());
                geciActivity.geci_biaoti.setText(bangdanList.get(musicId).getName());
                geciActivity.geci_biaoti.setText(bangdanList.get(musicId).getSinger_name());
                geciActivity.lrcBeanList = bangdanList.get(musicId).getLrcBeanList();
            }
        });
        //首页下一首监听事件
        shouye_next_music=findViewById(R.id.shouye_next_music);
        shouye_next_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tengxunPreferenceUtil.setPlayMusic(false);
                music_play.setImageResource(R.drawable.pause);
                next();
            }
        });
        //歌曲列表按钮监听事件
        ImageView music_list = findViewById(R.id.play_list);
        music_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overlay = findViewById(R.id.overlay);
                overlay.setBackgroundColor(Color.BLACK); // 设置蒙版颜色为黑色，可以根据需要调整
                overlay.setAlpha((float) 0.6); // 设置初始透明度为0
                MusicListFragement musicListFragement = new MusicListFragement(ShouyeActivity.this,overlay);
                musicListFragement.show(getSupportFragmentManager(),new View.OnClickListener() {
                    @Override
                    //打印一个按钮点击日志
                    public void onClick(View v) {
                        Log.d("MainActivity","primary onClick");
                    }
                },ShouyeActivity.this,overlay);
            }
        });
        //抽屉监听事件
            DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
            ImageView open = findViewById(R.id.major_drawer);
            RecyclerView recyclerView = findViewById(R.id.drawer_recyclerView);
            open.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DrawerAdapater song_listAdapater = new DrawerAdapater(ShouyeActivity.this);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShouyeActivity.this,LinearLayoutManager.VERTICAL,false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    //设置适配器到控件
                    recyclerView.setAdapter(song_listAdapater);
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            });
        //加载数据
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String str=MusicDao.data;
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
                    bangdanList=list;
//                    JSONObject jsonObject1 = new JSONObject(str);
//                    song=gson.fromJson(String.valueOf(jsonObject1),Song.class);
                }catch (JSONException e){
                    e.printStackTrace();
                }
                System.out.println(bangdanList.get(0).getName());
            }
        },3000);


    }
    public void next(){
        mediaPlayer.release();
        if(musicId>=bangdanList.size()-1){
            musicId=-1;
        }
        musicId++;
        try {
            if(geciActivity.timer!=null) {
                geciActivity.timer.cancel();
            }
            TengxunPreferenceUtil tengxunPreferenceUtil = new TengxunPreferenceUtil();
            MediaPlayer mediaPlayer1 = new MediaPlayer();
            mediaPlayer1.setDataSource(Quanju.url1+bangdanList.get(musicId).getUrl());
            mediaPlayer1.prepare();
            mediaPlayer = mediaPlayer1;
            mediaPlayer.start();
            if(tengxunPreferenceUtil.isPlayGEci()) {
                Timer timer1 = new Timer();
                geciActivity.timer = timer1;
                geciActivity.timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        int time;
                        try {
                            time = mediaPlayer.getCurrentPosition();
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
        }catch(IOException e){
            Log.d(TAG, bangdanList.get(musicId).getName());
        }
        Glide.with(this).load(Quanju.url1+bangdanList.get(musicId).getPic()).into(qia);
//        qia.setImageResource(musicXinxi.get(musicId).getPic());
//        String xinxi = musicXinxi.get(musicId).getBiaoti()+"-"+musicXinxi.get(musicId).getZhuozhe();
        shouye_zhuzhe.setText(bangdanList.get(musicId).getName());
    }

    private void tl_2() {
        mTabLayout_2.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
//                    UnreadMsgUtils.show(mTabLayout_2.getMsgView(0), mRandom.nextInt(100) + 1);
                }
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout_2.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setCurrentItem(0);
    }
    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

}