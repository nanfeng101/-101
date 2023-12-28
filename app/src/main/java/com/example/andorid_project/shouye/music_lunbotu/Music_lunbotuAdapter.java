package com.example.andorid_project.shouye.music_lunbotu;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.andorid_project.Guide.GuideFragment;

import java.util.ArrayList;

public class Music_lunbotuAdapter extends FragmentStatePagerAdapter {
    public Context context;
    public FragmentManager fragmentManager;
    public ArrayList<Integer> list = new ArrayList<Integer>();
    public Music_lunbotuAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
        this.fragmentManager=fragmentManager;

    }
    @Override
    public Fragment getItem(int position) {
        return Music_lunbotuFragment.newInstance(list.get(position));
    }

    @Override
    public int getCount() {
        return list.size();
    }
    //添加数据
    public void setData(ArrayList<Integer> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }
}
