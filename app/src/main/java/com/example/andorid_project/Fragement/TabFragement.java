package com.example.andorid_project.Fragement;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
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

import com.example.andorid_project.R;
import com.example.andorid_project.shouye.ShouyeActivity;
import com.example.andorid_project.shouye.apapter.DrawerAdapater;
import com.example.andorid_project.shouye.enity.MajorEnity;
import com.example.andorid_project.shouye.enity.ShouyeEnity;
import com.example.andorid_project.shouye.song_list.Song_listActivity;
import com.example.andorid_project.shouye.song_list.Song_listAdapater;
import com.example.andorid_project.shouye.until.ShouyeUntil;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.Random;


@SuppressLint("ValidFragment")
public class TabFragement extends Fragment{
    private Context mContext;

    private String mTitle;
    private View v;

    private ArrayList<Fragment> mFragments2 = new ArrayList<>();

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
        }else{
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
