package com.example.andorid_project.Fragement;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.andorid_project.Guide.GudieAdapter;
import com.example.andorid_project.LoginOrRegister.LoginActivity;
import com.example.andorid_project.R;
import com.example.andorid_project.config.Quanju;
import com.example.andorid_project.dao.shouye.MusicDao;
import com.example.andorid_project.dao.shouye.MvDao;
import com.example.andorid_project.dao.shouye.Song_listDao;
import com.example.andorid_project.shouye.ShouyeActivity;
import com.example.andorid_project.shouye.apapter.GedanAdapater;
import com.example.andorid_project.shouye.enity.Bangdan;
import com.example.andorid_project.shouye.enity.Mv;
import com.example.andorid_project.shouye.enity.Song_list;
import com.example.andorid_project.shouye.geci.LrcBean;
import com.example.andorid_project.shouye.geci.PraseLrc;
import com.example.andorid_project.shouye.music_lunbotu.Music_lunbotuAdapter;
import com.example.andorid_project.shouye.mv.MvAdapater;
import com.example.andorid_project.shouye.song_list.Song_listActivity;
import com.example.andorid_project.shouye.song_list.Song_listAdapater;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

@SuppressLint("ValidFragment")
public class MajorFragment extends Fragment {
    private String mTitle;
    private View v;
    private FragmentManager fragmentManager;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Context context;

    public static MajorFragment getInstance(String title, FragmentManager fragmentManager, Context context) {
        MajorFragment sf = new MajorFragment();
        System.out.println("qwertyuiop");
        sf.mTitle = title;
        sf.fragmentManager=fragmentManager;
        sf.context=context;
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mTitle.equals("音乐")) {
            Music_lunbotuAdapter music_lunbotuAdapter = new Music_lunbotuAdapter(fragmentManager);
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(R.drawable.major_lunbotu1);
            list.add(R.drawable.major_lunbotu2);
            list.add(R.drawable.major_lunbotu3);
            list.add(R.drawable.major_lunbotu4);
            list.add(R.drawable.major_lunbotu5);
            list.add(R.drawable.major_lunbotu6);
            list.add(R.drawable.major_lunbotu7);
            music_lunbotuAdapter.setData(list);
            v = inflater.inflate(R.layout.music, null);
            //设置适配器到控件
            ViewPager viewPager = (ViewPager) v.findViewById(R.id.major_lunbotu_vp);
            viewPager.setAdapter(music_lunbotuAdapter);
            //让指示器根据列表控件配合工作
            CircleIndicator circleIndicator = (CircleIndicator) v.findViewById(R.id.major_indicator);
            circleIndicator.setViewPager(viewPager);
            //适配器注册数据源观察者，以便列表变化时指示器知道
            music_lunbotuAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
            addSong_listData();
            //给首页音乐歌单列表添加数据
            addGedanList();

            System.out.println("MajorFragement");
            ImageView imageView1 = (ImageView) v.findViewById(R.id.shouye_song_list_pic1);
            imageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Song_listActivity.class);
                    intent.putExtra("id","1");
                    startActivity(intent);
                }
            });
            ImageView imageView2 = (ImageView) v.findViewById(R.id.shouye_song_list_pic2);
            imageView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Song_listActivity.class);
                    intent.putExtra("id","2");
                    startActivity(intent);
                }
            });
            ImageView imageView3 = (ImageView) v.findViewById(R.id.shouye_song_list_pic3);
            imageView3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Song_listActivity.class);
                    intent.putExtra("id","3");
                    startActivity(intent);
                }
            });
            ImageView imageView4 = (ImageView) v.findViewById(R.id.shouye_song_list_pic4);
            imageView4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Song_listActivity.class);
                    intent.putExtra("id","4");
                    startActivity(intent);
                }
            });
            ImageView imageView5 = (ImageView) v.findViewById(R.id.shouye_song_list_pic5);
            imageView5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Song_listActivity.class);
                    intent.putExtra("id","5");
                    startActivity(intent);
                }
            });
            return v;
        }else{
            v = inflater.inflate(R.layout.mv, null);
            MvDao mvDao = new MvDao();
            mvDao.getMv();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Mv song = new Mv();
                    List<Mv> list1 = new ArrayList<>();
                    Gson gson = new Gson();
                    try {
                        JSONArray jsonArray = new JSONArray(MvDao.data);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                            song=gson.fromJson(String.valueOf(jsonObject1),Mv.class);
                            list1.add(song);
                        }
                        MvAdapater mvAdapater = new MvAdapater(context);
                        mvAdapater.setData(list1);
                        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.mv_recyclerView);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);

                        recyclerView.setLayoutManager(linearLayoutManager);
                        //设置适配器到控件
                        recyclerView.setAdapter(mvAdapater);
//                    JSONObject jsonObject1 = new JSONObject(str);
//                    song=gson.fromJson(String.valueOf(jsonObject1),Song.class);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            },1000);
            return v;
        }

    }
    public void addSong_listData(){
        MusicDao musicDao = new MusicDao();
        musicDao.acquireSong_list("5");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<Song_list> list = MusicDao.songLists;
                int[] int1={R.id.shouye_song_list_pic1,R.id.shouye_song_list_pic2,R.id.shouye_song_list_pic3,R.id.shouye_song_list_pic4,R.id.shouye_song_list_pic5};
                int[] int2={R.id.shouye_song_list_playsum1,R.id.shouye_song_list_playsum2,R.id.shouye_song_list_playsum3,R.id.shouye_song_list_playsum4,R.id.shouye_song_list_playsum5};
                int[] int3={R.id.shouye_song_list_biaoti1,R.id.shouye_song_list_biaoti2,R.id.shouye_song_list_biaoti3,R.id.shouye_song_list_biaoti4,R.id.shouye_song_list_biaoti5};
                for(int i=0;i<int1.length;i++){
                    ImageView imageView = (ImageView) v.findViewById(int1[i]);
                    TextView textView1 = (TextView) v.findViewById(int2[i]);
                    TextView textView2 = (TextView) v.findViewById(int3[i]);
                    String str = Quanju.url1+list.get(i).getPic();
                    Glide.with(v).load(str).into(imageView);
                    textView1.setText(Integer.toString(list.get(i).getPlay_sum()));
                    textView2.setText(list.get(i).getTitle());
                }
            }
        },1000);
    }
    public void addGedanList(){
        MusicDao musicDao = new MusicDao();
        musicDao.acquireSong_list("10");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<Song_list> list = MusicDao.songLists;
                GedanAdapater gedanAdapater = new GedanAdapater(context);
                gedanAdapater.setData(list);
                RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.gedan_list_recyclerView);
                recyclerView.setNestedScrollingEnabled(false);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);

                recyclerView.setLayoutManager(gridLayoutManager);
                //设置适配器到控件
                recyclerView.setAdapter(gedanAdapater);
            }
        },1000);
    }
}
