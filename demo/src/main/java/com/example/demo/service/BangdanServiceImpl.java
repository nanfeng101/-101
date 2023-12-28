package com.example.demo.service;

import com.example.demo.entity.Bangdan;
import com.example.demo.entity.Song;
import com.example.demo.entity.SongByCon;
import com.example.demo.mapper.BangdanMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BangdanServiceImpl implements BangdanService{
    @Resource
    private BangdanMapper bangdanMapper;
    public List<Bangdan> getBangSong(String bangname){
//        System.out.println(bangname);
        List<Bangdan> list = new ArrayList<Bangdan>();
        if(bangname.equals("抖音热歌榜")) {
            list = bangdanMapper.getDouyin();
        }
        if(bangname.equals("酷狗飙升榜")) {
            list = bangdanMapper.getBiao();
        }
        if(bangname.equals("酷狗TOP500")) {
            list = bangdanMapper.getDouyin();
        }
//        list = bangdanMapper.getDouyin();
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
    //获取TOP500歌曲排名
    public Map<String,Object> getTopSong(SongByCon songByCon){
        Map<String,Object> map1 = new HashMap<String,Object>();
        List<Song> list = new ArrayList<Song>();
        int sum=songByCon.getPageSize()*(songByCon.getCurrentPage()-1);
        songByCon.setCurrentPage(sum);
        songByCon.setAct("byCon");
        list = bangdanMapper.getTopSong(songByCon);
        for(Song item:list){
            sum++;
            item.setSort_id(sum);
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
        map1.put("song",list);
        songByCon.setAct("byNoCon");
        map1.put("total1",bangdanMapper.getTopSong(songByCon).size());
        return map1;

    }
}
