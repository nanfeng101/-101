package com.example.andorid_project.Guide;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

import androidx.viewpager.widget.ViewPager;

import com.example.andorid_project.Activity.BaseCommonActivity;
import com.example.andorid_project.R;
import com.example.andorid_project.databinding.ActivityGuideBinding;
import com.example.andorid_project.shouye.ShouyeActivity;
import com.example.andorid_project.until.TengxunPreferenceUtil;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

public class GuideActivity extends BaseCommonActivity {
    private TengxunPreferenceUtil tengxunPreferenceUtil=new TengxunPreferenceUtil();
    public GudieAdapter gudieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        ActivityGuideBinding binding = ActivityGuideBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void initDatum() {
        super.initDatum();
        //创建适配器
        gudieAdapter = new GudieAdapter(this,getSupportFragmentManager());
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(R.drawable.aa);
        list.add(R.drawable.bb);
        list.add(R.drawable.cc);
        gudieAdapter.setData(list);
        //设置适配器到控件
        ViewPager viewPager = findViewById(R.id.list);
        viewPager.setAdapter(gudieAdapter);
        //让指示器根据列表控件配合工作
        CircleIndicator circleIndicator = findViewById(R.id.indicator);
        circleIndicator.setViewPager(viewPager);
        //适配器注册数据源观察者，以便列表变化时指示器知道
        gudieAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());


    }

    @Override
    protected void initListeners() {
        super.initListeners();
        //登录或注册按钮的监听器
        TextView loginOrRegister = findViewById(R.id.login_or_register);
        loginOrRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: loginOrRegister");
                tengxunPreferenceUtil.setAcceptUserAgreement();
                Intent intent = new Intent(GuideActivity.this, ShouyeActivity.class);
                intent.setAction("ACTION_LOGIN");
                startActivity(intent);
                finish();

            }
        });
        //立即体验按钮的监听器
        TextView experienceNow=findViewById(R.id.experience_now);
        experienceNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: experienceNow");
                tengxunPreferenceUtil.setAcceptUserAgreement();
                Intent intent = new Intent(GuideActivity.this, ShouyeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
