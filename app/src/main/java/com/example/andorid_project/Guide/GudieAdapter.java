package com.example.andorid_project.Guide;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class GudieAdapter extends FragmentStatePagerAdapter {
    public Context context;
    public FragmentManager fragmentManager;
    public ArrayList<Integer> list = new ArrayList<Integer>();
    public GudieAdapter(Context context, FragmentManager fragmentManager){
        super(fragmentManager);
        this.context=context;
        this.fragmentManager=fragmentManager;

    }
    @Override
    public Fragment getItem(int position) {
        return GuideFragment.newInstance(list.get(position));
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
