package com.example.andorid_project.shouye.apapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.andorid_project.shouye.Fragement.MusicListFragement;
import com.example.andorid_project.shouye.ShouyeActivity;
import com.example.andorid_project.shouye.geci.geciActivity;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.andorid_project.R;
import com.example.andorid_project.config.Quanju;
import com.example.andorid_project.shouye.enity.Bangdan;
import com.example.andorid_project.shouye.enity.Song_list;
import com.example.andorid_project.until.TengxunPreferenceUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder>{
    public Context context;
    public List<Bangdan> list = new ArrayList<>();
    public MusicListAdapter(Context context){
        this.context=context;
    }
    //添加数据
    public void setData(List<Bangdan> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public MusicListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.music_list_single,null);
        return new MusicListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MusicListAdapter.ViewHolder holder, int position) {
        Bangdan song = list.get(position);
        holder.bind(list,song,position,context);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public LinearLayout layout;
        public ViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(List<Bangdan> list1,Bangdan item, int id,Context context1){
            TextView textView = (TextView) itemView.findViewById(R.id.music_list_gequ);
            textView.setText(item.getName());
            layout = (LinearLayout) itemView.findViewById(R.id.music_list_single_play);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.music_list_single_delete);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TengxunPreferenceUtil tengxunPreferenceUtil = new TengxunPreferenceUtil();
                    MusicListFragement.musicListAdapter.list.remove(id);
                    MusicListFragement.musicListAdapter.notifyItemRemoved(id);
                    ShouyeActivity.bangdanList.remove(id);
                    String sum = "全部循环（"+ShouyeActivity.bangdanList.size()+"首）";
                    MusicListFragement.music_list_gequSum.setText(sum);
                    if(id==ShouyeActivity.musicId){
                        ShouyeActivity.mediaPlayer.release();
                        ShouyeActivity.music_play.setImageResource(R.drawable.play);
                        ShouyeActivity.shouye_zhuzhe.setText("云音乐，绽放生命之光");
                        ShouyeActivity.qia.setImageResource(R.drawable.weibofang);
                        tengxunPreferenceUtil.setPlayMusic(true);
                    }
                }
            });
            //歌曲播放监听事件
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(geciActivity.timer!=null){
                        geciActivity.timer.cancel();
                    }
                    TengxunPreferenceUtil tengxunPreferenceUtil = new TengxunPreferenceUtil();
                    try{
                        ShouyeActivity.mediaPlayer.release();
                        MediaPlayer mediaPlayer1 = new MediaPlayer();
                        mediaPlayer1.setDataSource(Quanju.url1+item.getUrl());
                        mediaPlayer1.prepare();
                        ShouyeActivity.mediaPlayer=mediaPlayer1;
                        ShouyeActivity.mediaPlayer.start();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    ShouyeActivity.shouye_zhuzhe.setText(item.getName());
                    Glide.with(context1).load(Quanju.url1+item.getPic()).into(ShouyeActivity.qia);
                    ShouyeActivity.music_play.setImageResource(R.drawable.pause);
                    ShouyeActivity.musicId=id;
                    if(geciActivity.geci_biaoti!=null) {
                        geciActivity.geci_play.setImageResource(R.drawable.pause_white);
                        geciActivity.geci_biaoti.setText(item.getName());
                        geciActivity.geci_zhuozhe.setText(item.getSinger_name());
                        geciActivity.lrcBeanList = item.getLrcBeanList();
                    }
                    tengxunPreferenceUtil.setPlayMusic(false);
                    if(tengxunPreferenceUtil.isPlayGEci()) {
                        Timer timer1 = new Timer();
                        geciActivity.timer = timer1;
                        geciActivity.timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                int time;
                                try {
                                    time = ShouyeActivity.mediaPlayer.getCurrentPosition();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    time = 0;
                                }
                                Message message = Message.obtain();
                                message.arg1 = time;
                                geciActivity.handler.sendMessage(message);
                            }
                        }, 500, 1000);
                    }
                }
            });
        }
    }
}
