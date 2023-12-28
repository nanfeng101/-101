package com.example.andorid_project.shouye.apapter;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Message;
import android.util.Log;
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
import com.example.andorid_project.shouye.ShouyeActivity;
import com.example.andorid_project.shouye.enity.Bangdan;
import com.example.andorid_project.shouye.enity.Song_list;
import com.example.andorid_project.shouye.geci.geciActivity;
import com.example.andorid_project.shouye.song_list.Song_listActivity;
import com.example.andorid_project.shouye.song_list.Song_listAdapater;
import com.example.andorid_project.until.TengxunPreferenceUtil;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GedanAdapater extends RecyclerView.Adapter<GedanAdapater.ViewHolder>{
    public Context context;
    public List<Song_list> list = new ArrayList<>();
    public GedanAdapater(Context context){
        this.context=context;

    }
    //添加数据
    public void setData(List<Song_list> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public GedanAdapater.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shouye_gedan_list,null);
        return new GedanAdapater.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GedanAdapater.ViewHolder holder, int position) {
        Song_list song = list.get(position);
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

        public void bind(Context context1,Song_list item, int id){
            imageView = (ImageView) itemView.findViewById(R.id.shouye_gedan_list_pic);
            Glide.with(itemView).load(Quanju.url1+item.getPic()).into(imageView);
            TextView shouye_gedan_biaoti=(TextView) itemView.findViewById(R.id.shouye_gedan_list_biaoti);
            shouye_gedan_biaoti.setText(item.getTitle());
            TextView shouye_gedan_playsum = (TextView) itemView.findViewById(R.id.shouye_gedan_list_sum);
            shouye_gedan_playsum.setText(Integer.toString(item.getPlay_sum()));
            layout = (LinearLayout) itemView.findViewById(R.id.shouye_gedan_list_gen);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context1, Song_listActivity.class);
                    intent.putExtra("id",Integer.toString(id+1));
                    context1.startActivity(intent);
                }
            });
        }
    }
}
