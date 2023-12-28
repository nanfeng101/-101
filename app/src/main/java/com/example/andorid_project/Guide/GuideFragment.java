package com.example.andorid_project.Guide;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.andorid_project.Fragement.BaseFragment;
import com.example.andorid_project.R;

public class GuideFragment extends BaseFragment {
        @Override
    protected void initDatum() {
        super.initDatum();
        int data = requireArguments().getInt("id");
        System.out.println(data);
        ImageView imageView = findViewById(R.id.icon);
        imageView.setImageResource(data);
    }

    @Override
    protected View getLayoutView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragement_guide,container,false);
    }

    public static GuideFragment newInstance(int data) {
        Bundle args = new Bundle();
        args.putInt("id",data);
        GuideFragment fragment = new GuideFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
