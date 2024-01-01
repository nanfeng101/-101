package com.example.andorid_project.LoginOrRegister;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andorid_project.Activity.BaseViewActivity;
import com.example.andorid_project.R;
import com.example.andorid_project.dao.shouye.Song_listDao;
import com.example.andorid_project.shouye.ShouyeActivity;
import com.example.andorid_project.shouye.geci.geciActivity;
import com.example.andorid_project.until.TengxunPreferenceUtil;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class YanzhengActivity extends BaseViewActivity {
    public TextView phoneText;
    public TextView timeText;
    public static int time=120;
    public static Timer timer;
    public Song_listDao song_listDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yanzheng);
    }

    @Override
    protected void initDatum() {
        super.initDatum();
        song_listDao = new Song_listDao();
        song_listDao.send(getIntent().getStringExtra("phone"));
        phoneText = findViewById(R.id.yanzhengma_phone);
        timeText = findViewById(R.id.yanzhengma_time);
        String str = "已发送至"+getIntent().getStringExtra("phone");
        phoneText.setText(str);
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(time==0){
                    timeText.setText("重新获取");
                    time=120;
                    timer.cancel();
                }else {
                    String str1 = time + "S";
                    timeText.setText(str1);
                    time--;
                }

            }
        },0,1000);

    }

    @Override
    protected void initListeners() {
        super.initListeners();
        ImageView imageView = findViewById(R.id.yanzhengma_fanhui);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timer!=null){
                    timer.cancel();
                }
                finish();
            }
        });
        TengxunPreferenceUtil tengxunPreferenceUtil = new TengxunPreferenceUtil();
        EditText editText = findViewById(R.id.yanzhengma);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(v.getId()==R.id.yanzhengma && hasFocus){
                    if(time!=120){
                        if(editText.getText().toString().equals(song_listDao.inform)){
                            if(!tengxunPreferenceUtil.isFirstLogin()){
                                Random random = new Random();
                                String randNum = "";
                                for (int i = 0; i<6; i++) {
                                    randNum += random.nextInt(10);
                                }
                                String username="用户"+randNum;
                                tengxunPreferenceUtil.setUserPic(R.drawable.user_pic);
                                tengxunPreferenceUtil.setUsername(username);
                                tengxunPreferenceUtil.setFirstLogin(true);
                                tengxunPreferenceUtil.setUserPhone(getIntent().getStringExtra("phone"));
                            }
                            tengxunPreferenceUtil.setUserLogin(true);
                            Intent intent = new Intent(YanzhengActivity.this,ShouyeActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast toast = Toast.makeText(YanzhengActivity.this,"验证码不对",Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                            toast.show();
                        }
                    }
                }
            }
        });
        timeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(time==120){
                    Log.d(TAG, "onClick: 重新发送验证码");
                    song_listDao = new Song_listDao();
                    song_listDao.send(getIntent().getStringExtra("phone"));
                    timer=new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            if(time==0){
                                timeText.setText("重新获取");
                                time=120;
                                timer.cancel();
                            }else {
                                String str1 = time + "S";
                                timeText.setText(str1);
                                time--;
                            }

                        }
                    },0,1000);
                }
            }
        });
    }
}