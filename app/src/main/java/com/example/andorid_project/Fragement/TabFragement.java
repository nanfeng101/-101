package com.example.andorid_project.Fragement;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.andorid_project.LoginOrRegister.LoginActivity;
import com.example.andorid_project.R;
import com.example.andorid_project.config.Quanju;
import com.example.andorid_project.dao.collect.MusicCollectDao;
import com.example.andorid_project.dao.collect.Song_list_CollectDao;
import com.example.andorid_project.myself.MySelfActivity;
import com.example.andorid_project.myself.adapater.MyselfGedanAdapater;
import com.example.andorid_project.shouye.ShouyeActivity;
import com.example.andorid_project.shouye.apapter.DrawerAdapater;
import com.example.andorid_project.shouye.enity.Bangdan;
import com.example.andorid_project.shouye.enity.MajorEnity;
import com.example.andorid_project.shouye.enity.ShouyeEnity;
import com.example.andorid_project.shouye.enity.Song_list;
import com.example.andorid_project.shouye.song_list.Song_listActivity;
import com.example.andorid_project.shouye.song_list.Song_listAdapater;
import com.example.andorid_project.shouye.until.ShouyeUntil;
import com.example.andorid_project.until.TengxunPreferenceUtil;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@SuppressLint("ValidFragment")
public class TabFragement extends Fragment{
    private Context mContext;

    private String mTitle;
    private View v;

    private ArrayList<Fragment> mFragments2 = new ArrayList<>();
    private Handler handler = new Handler(Looper.getMainLooper());
    private String[] shouyeTitles={"音乐","视频"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ViewPager majorPager;
    private CommonTabLayout majorTabLayout_2;
    private FragmentManager fragmentManager;
    private int id;

    public static TabFragement getInstance(String title,FragmentManager fragmentManager,Context context ){
        TabFragement sf = new TabFragement();
        sf.mTitle = title;
        sf.fragmentManager=fragmentManager;
        sf.mContext=context;
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public static void getfragement(FragmentManager fragmentManager){
        TabFragement sf = new TabFragement();
        sf.fragmentManager=fragmentManager;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mTitle.equals("首页")) {
            v = null;
            id++;
            View decorView = inflater.inflate(R.layout.activity_major, null);
            mFragments2.clear();
            mTabEntities.clear();
            majorPager = (ViewPager) decorView.findViewById(R.id.major_vp);
            MajorAdapter mAdapter = new MajorAdapter(fragmentManager);
            for (String title : shouyeTitles) {
                mFragments2.add(MajorFragment.getInstance(title,fragmentManager,mContext));
            }
            for (int i = 0; i < shouyeTitles.length; i++) {
                mTabEntities.add(new MajorEnity(shouyeTitles[i]));
            }
            mAdapter.update();
            majorPager.setAdapter(mAdapter);
//            vp.setAdapter(new MajorAdapter(fragmentManager));
            majorTabLayout_2 = (CommonTabLayout) decorView.findViewById(R.id.major_tl_2);
            majorTabLayout_2.setTabData(mTabEntities);
            tl_2();
            v = decorView;
//            fragmentTransaction.remove(yourFragment).addToBackStack(null);
        }else if(mTitle.equals("我的")){
            v = inflater.inflate(R.layout.activity_my_self ,null);
            TengxunPreferenceUtil tengxunPreferenceUtil1 = new TengxunPreferenceUtil();
            ImageView myself_user_pic = (ImageView) v.findViewById(R.id.myself_user_pic);
            TextView myself_user_name = (TextView) v.findViewById(R.id.myself_user_name);
            TextView myself_user_phone = (TextView) v.findViewById(R.id.myself_user_phone);
            ImageView myself_collect = (ImageView) v.findViewById(R.id.myself_collect);
            TextView myself_collect_sum = (TextView) v.findViewById(R.id.myself_collect_musicsum);
            if(tengxunPreferenceUtil1.isUserLogin()) {
                MusicCollectDao musicCollectDao = new MusicCollectDao();
                musicCollectDao.getAndroidCollectMusic(tengxunPreferenceUtil1.getUserPhone());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int sum = ShouyeActivity.collectList.size();
                        Glide.with(mContext).load(Quanju.url1+ShouyeActivity.collectList.get(sum-1).getPic()).into(myself_collect);
                        myself_collect_sum.setText(sum+"首");
                    }
                }, 1000);
            }
            //监听事件
            myself_user_pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(tengxunPreferenceUtil1.isUserLogin()){
                        Log.d(TAG, "onClick: 进入用户界面");
                    }else{
                        Log.d(TAG, "onClick: 进入登录界面");
                        Intent intent = new Intent(mContext, LoginActivity.class);
                        startActivity(intent);
                    }
                }
            });
            myself_user_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(tengxunPreferenceUtil1.isUserLogin()){
                        Log.d(TAG, "onClick: 进入用户界面");
                    }else{
                        Log.d(TAG, "onClick: 进入登录界面");
                        Intent intent = new Intent(mContext, LoginActivity.class);
                        startActivity(intent);
                    }
                }
            });
            if(tengxunPreferenceUtil1.isUserLogin()) {
                myself_user_pic.setImageResource(tengxunPreferenceUtil1.getUserPic());
                myself_user_name.setText(tengxunPreferenceUtil1.getUserName());
                String phone="账号："+tengxunPreferenceUtil1.getUserPhone();
                myself_user_phone.setText(phone);
                Song_list_CollectDao song_list_collectDao = new Song_list_CollectDao();
                song_list_collectDao.getAndroidUserCreateSongList(tengxunPreferenceUtil1.getUserPhone());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Song_list song = new Song_list();
                        List<Song_list> list1 = new ArrayList<>();
                        Gson gson = new Gson();
                        try {
                            JSONArray jsonArray = new JSONArray(song_list_collectDao.data);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                                song = gson.fromJson(String.valueOf(jsonObject1), Song_list.class);
                                list1.add(song);
                            }
                            MyselfGedanAdapater myselfGedanAdapater = new MyselfGedanAdapater(mContext);
                            myselfGedanAdapater.setData(list1);
                            RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.myself_recyclerView);
                            recyclerView.setNestedScrollingEnabled(false);
                            int heigh=list1.size()*210;
                            recyclerView.getLayoutParams().height=heigh;
                            recyclerView.requestLayout();
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);

                            recyclerView.setLayoutManager(linearLayoutManager);
                            //设置适配器到控件
                            recyclerView.setAdapter(myselfGedanAdapater);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, 1000);
            }
        }
        else{
            v = inflater.inflate(R.layout.mv ,null);
            TextView card_title_tv = (TextView) v.findViewById(R.id.content_mv);
            card_title_tv.setText(mTitle);
        }
        return v;
    }
    private void tl_2() {
        majorTabLayout_2.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                majorPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
//                    UnreadMsgUtils.show(mTabLayout_2.getMsgView(0), mRandom.nextInt(100) + 1);
                }
            }
        });

        majorPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                majorTabLayout_2.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        majorPager.setCurrentItem(0);
    }
    public final <T extends View> T findViewById(int id) {
        return requireView().findViewById(id);
    }
    private class MajorAdapter extends FragmentStatePagerAdapter {
        public MajorAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments2.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return shouyeTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments2.get(position);
        }
        private void update(){
            notifyDataSetChanged();
        }
    }
}
