package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.SongMapper;
import com.example.demo.mapper.Song_listMapper;
import com.example.demo.mapper.SystemhomeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class Song_listServiceImpl implements Song_listService{
    @Resource
    private Song_listMapper songListMapper;
    @Resource
    private SystemhomeMapper systemhomeMapper;
    @Resource
    private SongMapper songMapper;
    @Resource
    private CommentMapper commentMapper;
    //分页获取歌单信息
    public Map<String,Object> getSong_listPage(Song_listByCon song_listByCon){
        Map<String,Object> map1 = new HashMap<String,Object>();
        int a=song_listByCon.getPageSize();
        int sum=a*(song_listByCon.getCurrentPage()-1);
        song_listByCon.setCurrentPage(sum);
        if(song_listByCon.getStyle()==null){
            song_listByCon.setAct("byCon");
            map1.put("song_list",songListMapper.getSong_listPage(song_listByCon));
            song_listByCon.setAct("byNoCon");
            map1.put("total",songListMapper.getSong_listPage(song_listByCon).size());
            return map1;

        }
        song_listByCon.setAct("byCon");
        map1.put("song_list",songListMapper.getStyleSong_list(song_listByCon));
        song_listByCon.setAct("byNoCon");
        map1.put("total",songListMapper.getStyleSong_list(song_listByCon).size());
        return map1;
    }
    //更新歌单图片
    public void updateSong_listPic(String pic,int id){
        songListMapper.updateSong_listPic(pic,id);
    }
    //根据id获取歌单信息
    public Song_list getIdSong_list(int id){
        return songListMapper.getIdSong_list(id);
    }
    //修改歌单信息
    public String updateSong_list(Song_list songList){
        if(songListMapper.updateSong_list(songList)>0){
            return "ok";
        }
        return "no";
    }
    //删除歌单信息
    public String deleteSong_list(int id){
        if(songListMapper.deleteSong_list(id)>0){
            return "ok";
        }
        return "no";
    }
    //添加歌单信息
    public String addSong_list(Song_list songList){
        if(songListMapper.addSong_list(songList)>0){
            return "ok";
        }
        return "no";
    }
    //根据歌单主题查找歌单
    public Map<String,Object> selectTitleSong_list(String title){
        Map<String,Object> map1 = new HashMap<String ,Object>();
        map1.put("song_list",songListMapper.selectTitleSong_list(title));
        map1.put("total",songListMapper.selectTitleSong_list(title).size());
        return map1;
    }
    //根据歌单id查找其里的歌曲
    public Map<String,Object> getSong_listContent(int id){
        Map<String ,Object> map1 = new HashMap<String,Object>();
        map1.put("song_list",songListMapper.getSong_listContent(id));
        return map1;
    }
    //删除歌单里的歌曲
    public String deleteSong_ListSong(int id){
        System.out.println(id);
        if(songListMapper.deleteSong_ListSong(id)>0){
            return "ok";
        }
        return "no";
    }
    //获取歌单,歌手,评论的信息，并传给前台
    public Map<String,Object> getSong_list_to_qianttai(int id){
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("song_list",songListMapper.getSong_list_to_qianttai(id));
        List<Song> list = songMapper.getSong_list_to_qianttai2(id);
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
//        List<Comment> list2 = commentMapper.getSong_listComment(id);
//        for(Comment item1:list2){
//            Date birth = item1.getCreate_time();
//            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            String birth1=dateFormat.format(birth);
//            item1.setData(birth1);
//        }
//        map1.put("comment",list2);
        map1.put("song",list);
        return map1;
    }

    //安卓调用服务
    public List<Song_list> getAndroidSong_listPage(Song_listByCon song_listByCon){
        Map<String,Object> map1 = new HashMap<String,Object>();
        int a=song_listByCon.getPageSize();
        int sum=a*(song_listByCon.getCurrentPage()-1);
        song_listByCon.setCurrentPage(sum);
        song_listByCon.setAct("byCon");
        return songListMapper.getAndroidSong_listPage(song_listByCon);
    }
    public List<Bangdan> getAndroidSong_listMusic(int id){
        List<Bangdan> list = songListMapper.getAndroidSong_listMusic(id);
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
