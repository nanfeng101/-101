package com.example.demo.service;

import com.example.demo.entity.Singer;
import com.example.demo.entity.SingerByCon;
import com.example.demo.entity.Song;
import com.example.demo.mapper.SingerMapper;
import com.example.demo.mapper.SongMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SingerServiceImpl implements SingerService{
    @Resource
    private SingerMapper singerMapper;
    @Resource
    private SongMapper songMapper;
    //获取歌手信息
    public Map<String,Object> selectSingerPage(SingerByCon singerByCon){
        Map<String,Object> map = new HashMap<String,Object>();
        int a=singerByCon.getPageSize();
        int sum=a*(singerByCon.getCurrentPage()-1);
        singerByCon.setCurrentPage(sum);
        if(singerByCon.getSex()==0) {
            singerByCon.setAct("byPage");
            map.put("singer",singerMapper.selectSingerPage(singerByCon));
            singerByCon.setAct("byNoPage");
            map.put("total",singerMapper.selectSingerPage(singerByCon).size());
            return map;
        }
        singerByCon.setAct("byPage");
        map.put("singer",singerMapper.getSexSingerPage(singerByCon));
        singerByCon.setAct("byNoPage");
        map.put("total",singerMapper.getSexSingerPage(singerByCon).size());
        return map;
    }
    //查找歌手信息
    public Map<String,Object> selectSinger(String name){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("singer",singerMapper.selectSinger(name));
        map.put("total",singerMapper.selectSinger(name));
        return map;
    }
    //更新歌手图片
    public void updatePic(String pic,int id){

        singerMapper.updatePic(pic,id);
//        songMapper.updateSongPic(pic,id);
    }

    //添加歌手信息
    public String addSinger(Singer singer){
        System.out.println(singer.getBirth());
        if(singerMapper.addSinger(singer)>0){
            return "ok";
        }
        return "no";
    }
    //删除歌手信息
    public String deleteSinger(int id){
        if(singerMapper.deleteSinger(id)>0){
            return "ok";
        }
        return "no";
    }
    //根据id获得歌手信息
    public  Singer getIdSinger(int id){
//        Map<String,Object> map1 = new HashMap<String,Object>();
//        map1.put("singer1",singerMapper.getIdSinger(id));
        return singerMapper.getIdSinger(id);
    }
    //修改歌手信息
    public String updateSinger(Singer singer){
        if(singerMapper.updateSinger(singer)>0){
            return "ok";
        }
        return "no";
    }
    //根据歌手id获取其信息到歌手点开后的界面
    public Map<String,Object> getSong_to_qiantai(int id){
        Map<String,Object> map1 = new HashMap<String,Object>();
        List<Singer> list1 = singerMapper.getSong_to_qiantai(id);
        for(Singer item1:list1){
            Date birth = item1.getBirth();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String birth1=dateFormat.format(birth);
            item1.setBirth1(birth1);
        }
        map1.put("singer",list1);
        List<Song> list = songMapper.getSong_to_qiantai2(id);
        for(Song item:list){
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
        return map1;

    }
}
