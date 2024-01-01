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
import com.example.andorid_project.shouye.apapter.GedanAdapater;
import com.example.andorid_project.shouye.enity.Song_list;
import com.example.andorid_project.shouye.song_list.Song_listActivity;

import java.util.ArrayList;
import java.util.List;

public class MyselfGedanAdapater extends RecyclerView.Adapter<MyselfGedanAdapater.ViewHolder> {
    public Context context;
    public List<Song_list> list = new ArrayList<>();
    public MyselfGedanAdapater(Context context){
        this.context=context;

    }
    //添加数据
    public void setData(List<Song_list> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public MyselfGedanAdapater.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.myself_gedan_list,null);
        return new MyselfGedanAdapater.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyselfGedanAdapater.ViewHolder holder, int position) {
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
            imageView = (ImageView) itemView.findViewById(R.id.myself_gedan_pic);
            Glide.with(itemView).load(Quanju.url1+item.getPic()).into(imageView);
            TextView shouye_gedan_biaoti=(TextView) itemView.findViewById(R.id.myself_gedan_biaoti);
            shouye_gedan_biaoti.setText(item.getTitle());
            layout = (LinearLayout) itemView.findViewById(R.id.myself_linearlayout);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context1, Song_listActivity.class);
                    intent.putExtra("id",Integer.toString(item.getId()));
                    context1.startActivity(intent);
                }
            });
        }
    }
}
