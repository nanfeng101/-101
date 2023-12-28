package com.example.andorid_project.shouye.apapter;

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
import com.example.andorid_project.shouye.enity.Song_list;
import com.example.andorid_project.shouye.song_list.Song_listActivity;

import java.util.ArrayList;
import java.util.List;

public class DrawerAdapater extends RecyclerView.Adapter<DrawerAdapater.ViewHolder> {
    public Context context;
    public String[] list = {"福利中心","消息中心","设置","定时关闭","音乐闹钟","我的客服","个人信息与隐私保护","关于","退出登录"};
    public int[] iconint = {R.drawable.fuli,R.drawable.inform,R.drawable.shezhi,R.drawable.dingshi_close,R.drawable.clock,R.drawable.kefu,R.drawable.protect,R.drawable.guanyu,R.drawable.tuichu_login};
    public DrawerAdapater(Context context){
        this.context=context;

    }

    @Override
    public DrawerAdapater.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.drawer_content,null);
        return new DrawerAdapater.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DrawerAdapater.ViewHolder holder, int position) {
        holder.bind(list,iconint,context,position);

    }

    @Override
    public int getItemCount() {
        return list.length;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public LinearLayout layout;
        public ViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(String[] str,int[] iconarr,Context context1, int id){
            TextView content = (TextView) itemView.findViewById(R.id.drawer_content_tool);
            content.setText(str[id]);
            ImageView iconView = (ImageView) itemView.findViewById(R.id.drawer_content_icon);
            iconView.setImageResource(iconarr[id]);
        }
    }
}
