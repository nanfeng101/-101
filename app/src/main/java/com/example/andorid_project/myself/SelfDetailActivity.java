package com.example.andorid_project.myself;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.Resource;
import com.example.andorid_project.Activity.BaseViewActivity;
import com.example.andorid_project.R;
import com.example.andorid_project.dao.UserDao;
import com.example.andorid_project.until.TengxunPreferenceUtil;

import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class SelfDetailActivity extends BaseViewActivity {
    private final TengxunPreferenceUtil tengxunPreferenceUtil = new TengxunPreferenceUtil();
    private int PICK_UID;
    private ImageView userpic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_detail);
    }

    @Override
    protected void initDatum() {
        super.initDatum();


    }

    @Override
    protected void initListeners() {
        super.initListeners();
        ImageView fanhui = findViewById(R.id.self_detail_fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        userpic = findViewById(R.id.self_detail_userPic);
        userpic.setImageResource(tengxunPreferenceUtil.getUserPic());
        userpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                String str="";
                for(int i=0;i<6;i++){
                    str=str+random.nextInt(10);
                }
                PICK_UID=Integer.parseInt(str);
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_UID);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == PICK_UID && resultCode == RESULT_OK && data!=null && data.getData()!=null){
            Uri uri = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                userpic.setImageBitmap(bitmap);
                UserDao userDao = new UserDao();
                userDao.userUploadPic(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}