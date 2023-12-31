package com.example.andorid_project.LoginOrRegister;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andorid_project.Activity.BaseViewActivity;
import com.example.andorid_project.R;
import com.example.andorid_project.dao.shouye.Song_listDao;

public class LoginActivity extends BaseViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        EditText phone = findViewById(R.id.editTextNumberPassword);
        Button login = findViewById(R.id.login_buton);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(80);
        login.setBackground(gradientDrawable);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(phone.getText().toString().length()==0){
                    System.out.println("手机号为空");
                    Toast toast = Toast.makeText(LoginActivity.this,"请输入手机号",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                    toast.show();
                }
                else if(phone.getText().toString().length()<11){
                    System.out.println("手机号为空");
                    Toast toast = Toast.makeText(LoginActivity.this,"请输入11位数字的手机号",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                    toast.show();
                }
                else{
                    Intent intent = new Intent(LoginActivity.this,YanzhengActivity.class);
                    intent.putExtra("phone",phone.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }
}