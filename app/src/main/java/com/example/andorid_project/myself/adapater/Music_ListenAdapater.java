package com.example.andorid_project.myself.adapater;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.andorid_project.R;
import com.example.andorid_project.config.Quanju;
import com.example.andorid_project.shouye.enity.Bangdan;
import com.example.andorid_project.shouye.enity.Song_list;
import com.example.andorid_project.shouye.song_list.Song_listActivity;

import java.util.ArrayList;
import java.util.List;

public class Music_ListenAdapater extends RecyclerView.Adapter<Music_ListenAdapater.ViewHolder> {
    public Context context;
    public List<Bangdan> list = new ArrayList<>();
    public Music_ListenAdapater(Context context){
        this.context=context;

    }
    //添加数据
    public void setData(List<Bangdan> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public Music_ListenAdapater.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.music_listen_single,null);
        return new Music_ListenAdapater.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Music_ListenAdapater.ViewHolder holder, int position) {
        Bangdan song = list.get(position);
        holder.bind(context,song,position);

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

        public void bind(Context context1, Bangdan item, int id){
            TextView rang = (TextView) itemView.findViewById(R.id.music_listen_rank);
            TextView name = (TextView) itemView.findViewById(R.id.music_listen_name);
            TextView singer = (TextView) itemView.findViewById(R.id.music_listen_singer);
            TextView sum = (TextView) itemView.findViewById(R.id.music_listen_sum);
            rang.setText(Integer.toString(id+1));
            name.setText(item.getName());
            singer.setText(item.getSinger_name());
            String str = item.getSum()+"次播放";
            sum.setText(str);
            layout = (LinearLayout) itemView.findViewById(R.id.music_listen_layout);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Quanju.PlayMusic(context1,item,id);
                }
            });
        }
    }
}
