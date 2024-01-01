package com.example.demo.service;

import com.example.demo.entity.Bangdan;
import com.example.demo.entity.Consumer;
import com.example.demo.entity.Song_list;
import com.example.demo.mapper.CollectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class CollectServiceImpl implements CollectService{
    @Resource
    private CollectMapper collectMapper;
    //根据歌手id查找其收藏歌曲
    public Map<String,Object> getCollect(int id){
        Map<String ,Object> map1 = new HashMap<String,Object>();
        map1.put("collect",collectMapper.getCollect(id));
        return map1;
    }
    //删除用户收藏歌曲
    public String deleteCollect(int id){
        if(collectMapper.deleteCollect(id)>0){
            return "ok";
        }
        return "no";
    }
    //用户收藏歌单
    public String Song_listShouCang(int id1,int id2){
        if(collectMapper.isSong_listExit(id1,id2)==null) {
            if (collectMapper.Song_listShouCang(id1, id2) > 0) {
                return "ok";
            }
            else {
                return "no";
            }
        }
        else {
            return "no";
        }
    }
    //用户收藏mv
    public String MvShouCang(int id1,int id2){
        if(collectMapper.isMvExit(id1,id2)==null) {
            if (collectMapper.MvShouCang(id1, id2) > 0) {
                return "ok";
            }
            else {
                return "no";
            }
        }else {
            return "no";
        }
    }
    //关注歌手
    public String Attention_singer(int id1,int id2){
        if(collectMapper.isSingerExit(id1,id2)==null) {
            if (collectMapper.Attention_singer(id1,id2) > 0) {
                return "ok";

            }else {
                return "no";
            }
        }else {
            return "no";
        }
    }

    //安卓调用方法
    //获取用户创建歌单
    public List<Song_list> getAndroidUserCreateSongList(String phone){
        Consumer consumer = collectMapper.selectAndroidUserId(phone);
        return collectMapper.getAndroidUserCreateSongList(consumer.getId());
    }
    public List<Bangdan> getAndroidUserCollectMusic(String phone){
        Consumer consumer = collectMapper.selectAndroidUserId(phone);
        List<Bangdan> list = collectMapper.getAndroidUserCollectMusic(consumer.getId());
        for(Bangdan item:list){
            String name1=item.getName();
            char[] name2=name1.toCharArray();
            String name3="";
            String name4="";
            int m=0;
            for(int i=0;i<name2.length;i++){
                if(name2[i]=='-'){
                    m=i;
                    break;
                }
                name3=name3+name2[i];
            }
            item.setSinger_name(name3);
            for(int j=m+1;j<name2.length;j++){
                name4=name4+name2[j];
            }
            item.setName(name4);
        }
        return list;
    }
}
