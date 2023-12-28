package com.example.andorid_project.shouye.song_list;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.SyncAdapterType;
import android.media.MediaPlayer;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.bumptech.glide.Glide;
import com.example.andorid_project.Guide.GuideFragment;
import com.example.andorid_project.R;
import com.example.andorid_project.config.Quanju;
import com.example.andorid_project.shouye.ShouyeActivity;
import com.example.andorid_project.shouye.geci.geciActivity;
import com.example.andorid_project.shouye.enity.Bangdan;
import com.example.andorid_project.shouye.enity.Song;
import com.example.andorid_project.until.TengxunPreferenceUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Song_listAdapater extends RecyclerView.Adapter<Song_listAdapater.ViewHolder> {
    public Context context;
    public List<Bangdan> list = new ArrayList<>();
    public Song_listAdapater(Context context){
        this.context=context;

    }
    //添加数据
    public void setData(List<Bangdan> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.song_list_music,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Bangdan song = list.get(position);
        holder.bind(list,song,position,context);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public LinearLayout layout;
        public ViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(List<Bangdan> list1,Bangdan item, int id,Context context1){
            imageView=(ImageView) itemView.findViewById(R.id.song_list_music_pic);
            layout = (LinearLayout) itemView.findViewById(R.id.song_list_music_gen);
            TextView textView1 = (TextView) itemView.findViewById(R.id.song_list_music_biaoti);
            textView1.setText(item.getName());
            TextView textView2 = (TextView) itemView.findViewById(R.id.song_list_music_author);
            textView2.setText(item.getSinger_name());
            Glide.with(itemView).load(Quanju.url1+item.getPic()).into(imageView);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: 歌曲已经被点击");
                    ShouyeActivity.bangdanList.addAll(list1);
                    System.out.println(id);
                    ShouyeActivity.musicId=id;
                    if(geciActivity.timer!=null){
                        geciActivity.timer.cancel();
                    }
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    try {
                        mediaPlayer.setDataSource(Quanju.url1 + item.getUrl());
                        mediaPlayer.prepare();
                        ShouyeActivity.mediaPlayer=mediaPlayer;
                        ShouyeActivity.mediaPlayer.start();
                    }
                    catch (IOException e){

                    }
                    Glide.with(context1).load(Quanju.url1+item.getPic()).into(ShouyeActivity.qia);
                    ShouyeActivity.shouye_zhuzhe.setText(item.getName());
                    if(geciActivity.geci_biaoti!=null) {
                        geciActivity.geci_biaoti.setText(item.getName());
                        geciActivity.geci_zhuozhe.setText(item.getSinger_name());
                        geciActivity.lrcBeanList = item.getLrcBeanList();
                    }
                    TengxunPreferenceUtil tengxunPreferenceUtil = new TengxunPreferenceUtil();
                    tengxunPreferenceUtil.setPlayMusic(false);
                    ShouyeActivity.music_play.setImageResource(R.drawable.pause);
                    if(geciActivity.timer!=null) {
                        Timer timer = new Timer();
                        try {
                            geciActivity.timer = timer;
                            geciActivity.timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    int time;
                                    try {
                                        time = ShouyeActivity.mediaPlayer.getCurrentPosition();
                                    } catch (Exception e) {
                                        time = 0;
                                    }
                                    Message message = Message.obtain();
                                    message.arg1 = time;
                                    geciActivity.handler.sendMessage(message);

                                }
                            }, 200, 1000);
                        } catch (Exception e) {

                        }
                    }
                }
            });
        }
    }
}
