package com.example.demo.service;

import com.example.demo.mapper.CollectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
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
}
