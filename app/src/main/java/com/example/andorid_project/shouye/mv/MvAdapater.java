package com.example.andorid_project.shouye.mv;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.andorid_project.R;
import com.example.andorid_project.config.Quanju;
import com.example.andorid_project.shouye.enity.Bangdan;
import com.example.andorid_project.shouye.enity.Mv;

import java.util.ArrayList;
import java.util.List;

public class MvAdapater extends RecyclerView.Adapter<MvAdapater.ViewHolder> {
    public Context context;
    public List<Mv> list = new ArrayList<>();
    public MvAdapater(Context context){
        this.context=context;
    }
    //添加数据
    public void setData(List<Mv> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public MvAdapater.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mv_single,null);
        return new MvAdapater.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MvAdapater.ViewHolder holder, int position) {
        Mv song = list.get(position);
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

        public void bind(List<Mv> list1,Mv item, int id,Context context1){
            TextView introduce = (TextView) itemView.findViewById(R.id.mv_biaoti);
            introduce.setText(item.getIntroduce());
            TextView playsum = (TextView) itemView.findViewById(R.id.mv_playsum);
            playsum.setText(Integer.toString(item.getPlay_sum()));
            ImageView imageView = (ImageView) itemView.findViewById(R.id.mv_image);
            Glide.with(context1).load(Quanju.url1+item.getPic()).into(imageView);
            VideoView videoView = (VideoView) itemView.findViewById(R.id.mv_playVideo);
            videoView.setVisibility(View.INVISIBLE);
            ImageView background = (ImageView) itemView.findViewById(R.id.mv_playbackground);
            background.setVisibility(View.INVISIBLE);
            LinearLayout mv_layout = (LinearLayout) itemView.findViewById(R.id.mv_layout);
            mv_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse(Quanju.url1+item.getUrl());
                    videoView.setVisibility(View.VISIBLE);
                    background.setVisibility(View.VISIBLE);
                    videoView.setMediaController(new MediaController(context1));
                    videoView.setVideoURI(uri);
                    videoView.start();
                }
            });
        }
    }
}
