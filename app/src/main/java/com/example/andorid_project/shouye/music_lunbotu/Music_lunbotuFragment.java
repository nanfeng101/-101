package com.example.andorid_project.shouye.music_lunbotu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.andorid_project.Fragement.BaseFragment;
import com.example.andorid_project.Guide.GuideFragment;
import com.example.andorid_project.R;

public class Music_lunbotuFragment extends BaseFragment {
    protected void initDatum() {
        super.initDatum();
        int data = requireArguments().getInt("id");
        System.out.println(data);
        ImageView imageView = findViewById(R.id.lunbotu_icon);
        imageView.setImageResource(data);
    }

    @Override
    protected View getLayoutView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.music,container,false);
    }

    public static GuideFragment newInstance(int data) {

        Bundle args = new Bundle();
        args.putInt("id",data);
        GuideFragment fragment = new GuideFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
