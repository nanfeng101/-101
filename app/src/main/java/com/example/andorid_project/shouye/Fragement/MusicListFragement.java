package com.example.andorid_project.shouye.Fragement;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Process;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.andorid_project.Fragement.BaseDialogFragment;
import com.example.andorid_project.R;
import com.example.andorid_project.dao.shouye.MusicDao;
import com.example.andorid_project.shouye.ShouyeActivity;
import com.example.andorid_project.shouye.apapter.GedanAdapater;
import com.example.andorid_project.shouye.apapter.MusicListAdapter;
import com.example.andorid_project.shouye.enity.Bangdan;
import com.example.andorid_project.shouye.enity.Song_list;
import com.example.andorid_project.shouye.song_list.Song_listActivity;
import com.example.andorid_project.startpage.TermServiceDialogFragment;
import com.example.andorid_project.until.TengxunPreferenceUtil;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class MusicListFragement extends BaseDialogFragment {
    private View.OnClickListener onClickListener;
    private Context context;
    private View v;
    private FrameLayout frameLayout;
    private FragmentManager fragmentManager1;
    public static MusicListAdapter musicListAdapter;
    public static TextView music_list_gequSum;

    @Override
    protected void initViews() {
        super.initViews();
        //设置触摸其它地方对话框不会消失
        setCancelable(false);

    }
    public MusicListFragement(Context con, FrameLayout frameLayout1){
        context=con;
        frameLayout=frameLayout1;
    }

    @Override
    protected void initDatum() {
        super.initDatum();
        music_list_gequSum = findViewById(R.id.music_list_gequSum);
        String sum = "全部循环（"+ShouyeActivity.bangdanList.size()+"首）";
        music_list_gequSum.setText(sum);
        musicListAdapter = new MusicListAdapter(context);
        musicListAdapter.setData(ShouyeActivity.bangdanList);
        RecyclerView recyclerView = findViewById(R.id.musicList_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器到控件
        recyclerView.setAdapter(musicListAdapter);
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        TextView textView = findViewById(R.id.music_list_close);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameLayout.setBackgroundColor(Color.WHITE); // 设置蒙版颜色为黑色，可以根据需要调整
                frameLayout.setAlpha(0); // 设置初始透明度为0
                dismiss();
            }
        });
        ImageView music_list_all_delete = findViewById(R.id.music_list_all_delete);
        music_list_all_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TengxunPreferenceUtil tengxunPreferenceUtil = new TengxunPreferenceUtil();
                List<Bangdan> list = new ArrayList<>();
                musicListAdapter.setData(list);
                ShouyeActivity.bangdanList=list;
                String sum = "全部循环（"+ShouyeActivity.bangdanList.size()+"首）";
                music_list_gequSum.setText(sum);
                ShouyeActivity.mediaPlayer.release();
                ShouyeActivity.music_play.setImageResource(R.drawable.play);
                ShouyeActivity.shouye_zhuzhe.setText("云音乐，绽放生命之光");
                ShouyeActivity.qia.setImageResource(R.drawable.weibofang);
                tengxunPreferenceUtil.setPlayMusic(true);
            }
        });
     }

    @Override
    protected View getLayoutView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //将一个xml转为一个View
        v=inflater.inflate(R.layout.music_list,container,false);
        return v;
    }
    public void show(FragmentManager fragmentManager, View.OnClickListener onClickListener, Context con, FrameLayout frameLayout1){
        MusicListFragement musicListFragement = new MusicListFragement(con,frameLayout1);
        musicListFragement.onClickListener=onClickListener;
        musicListFragement.show(fragmentManager,"TermServiceDialogFragment");
    }
}
