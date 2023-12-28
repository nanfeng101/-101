package com.example.andorid_project;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import com.example.andorid_project.Activity.BaseLogicActivity;
import com.example.andorid_project.Guide.GuideActivity;
import com.example.andorid_project.databinding.ActivityMainBinding;
import com.example.andorid_project.shouye.ShouyeActivity;
import com.example.andorid_project.startpage.TermServiceDialogFragment;
import com.example.andorid_project.until.DefaultPreferenceUtil;
import com.example.andorid_project.until.TengxunPreferenceUtil;
import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.RequestCallback;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.List;

public class MainActivity extends BaseLogicActivity {
    private Handler handler = new Handler(Looper.getMainLooper());
    private TengxunPreferenceUtil tengxunPreferenceUtil = new TengxunPreferenceUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        ActivityMainBinding binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void initViews() {
        super.initViews();
        QMUIStatusBarHelper.translucent(this);
    }

    @Override
    protected void initDatum() {
        super.initDatum();
        //用来判断是否显示用户协议
        if(DefaultPreferenceUtil.getInstance(this).isAcceptUserAgreement()){
            //已经同意了用户协议
            requestPermission();  //用于获取用户手机的权限

        }else {
            showTermServiceAgreementDualog();
        }
    }

    /**
     * 该方法用于获取用户手机的权限
     * Manifest.permission.CAMERA,  相机权限
     * Manifest.permission.RECORD_AUDIO,  麦克风权限
     *Manifest.permission.ACCESS_COARSE_LOCATION,   模糊定位权限
     *Manifest.permission.ACCESS_FINE_LOCATION   精度定位权限
     * Manifest.permission.READ_PHONE_STATE   读取手机的状态
     */
    private void requestPermission(){
        PermissionX.init(this).permissions(
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_PHONE_STATE
        ).request(new RequestCallback() {
            @Override
            //用户两次拒绝权限申请之后，软件将无法打开
            public void onResult(boolean allGranted, List<String> grantedList,List<String> deniedList) {
                if(allGranted){
                    //延迟1s后执行,这样就可以看见启动界面
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            preparNext();
                        }
                    },1000);
                }
                else{
                    finish();
                }
            }
        });

    }

    private void  preparNext(){
        Log.d(TAG, "preparNext: ");
        if(tengxunPreferenceUtil.isAcceptUserAgreement()) {  //判断是否要显示引导界面
            Log.d(TAG, "preparNext: 前往下一个界面");
            Intent intent = new Intent(this, ShouyeActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Intent intent = new Intent(this, GuideActivity.class);
            startActivity(intent);
            finish();   //关闭当前界面,防止用户返回上一个界面
        }
    }

    private void showTermServiceAgreementDualog(){
        TermServiceDialogFragment dialogFragment = new TermServiceDialogFragment();
        dialogFragment.show(getSupportFragmentManager(),new View.OnClickListener() {
            @Override
            //打印一个按钮点击日志
            public void onClick(View v) {
                Log.d("MainActivity","primary onClick");
                DefaultPreferenceUtil.getInstance(new MainActivity()).setAcceptUserAgreement();
                requestPermission();
            }
        });
    }

}