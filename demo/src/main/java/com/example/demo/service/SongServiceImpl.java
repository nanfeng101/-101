package com.example.demo.service;

import com.example.demo.entity.SearchSong;
import com.example.demo.entity.Singer;
import com.example.demo.entity.Song;
import com.example.demo.entity.SongByCon;
import com.example.demo.mapper.SingerMapper;
import com.example.demo.mapper.SongMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SongServiceImpl implements SongService{
    @Resource
    private SongMapper songMapper;
    @Resource
    private SingerMapper singerMapper;
    public Map<String,Object> getSongPage(SongByCon songByCon){
        Map<String,Object> map1 = new HashMap<String,Object>();
        int sum=5*(songByCon.getCurrentPage()-1);
        songByCon.setCurrentPage(sum);
        songByCon.setAct("byCon");
        map1.put("song",songMapper.getSongPage(songByCon));
        songByCon.setAct("byNoCon");
        map1.put("total1",songMapper.getSongPage(songByCon).size());
        return map1;
    }
    //更新歌手图片
    public void updateSongPic(String pic,int id){
//        singerMapper.updatePic(pic,id);
        songMapper.updateSongPic(pic,id);
    }
    //删除歌曲
    public String deleteSong(int id){
        if(singerMapper.deleteSinger(id)>0){
            return "ok";
        }
        return "no";
    }
    //根据id获得歌曲信息
    public Song getIdSong(int id){
//        Map<String,Object> map1 = new HashMap<String,Object>();
//        map1.put("singer1",singerMapper.getIdSinger(id));
        return songMapper.getIdSong(id);
    }
    //修改歌曲信息
    public String updateSong(Song song){
        if(songMapper.updateSong(song)>0){
            return "ok";
        }
        return "no";
    }
    //添加歌曲
    public String addSong(Song song)throws Exception{
        Singer singer = singerMapper.searchNameId(song.getSinger_name());
        int id=singer.getId();
        song.setSinger_id(id);
        MultipartFile myfile = song.getMpfile();
        String path = ResourceUtils.getURL("classpath:").getPath() + "static";
        String fileName = myfile.getOriginalFilename();
        String giamg = fileName;
        File filePath = new File(path + File.separator + fileName);
        myfile.transferTo(filePath);
        song.setUrl(giamg);
        if(songMapper.addSong(song)>0){
            return "ok";
        }
        return "no";

    }
    //更新歌曲
    public void updateSongUrl(String url,int id){
        songMapper.updateSongUrl(url,id);
    }
    //搜索歌曲
    public Map<String,Object> searchSong(SearchSong searchSong){
        Map<String,Object> map1 = new HashMap<String,Object>();
        if(searchSong.getVal()!=null) {
            if (searchSong.getVal_id() == 1) {
                Singer singer = singerMapper.getNameSinger(searchSong.getVal());
                if(singer!=null){
                    return map1;
                }
                int id = singer.getId();
                map1.put("song", songMapper.searchSongSinger_id(id));
                map1.put("total", songMapper.searchSongSinger_id(id).size());
                return map1;
            } else {
                map1.put("song", songMapper.searchSongName(searchSong.getVal()));
                map1.put("total", songMapper.searchSongName(searchSong.getVal()).size());
                return map1;
            }
        }
        return map1;
    }
    //前台搜索
    public List<Song> classifysong(String name){
        List<Song> list1 = songMapper.searchSongName(name);
        for(Song item:list1){
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
        return list1;
    }
    //修改歌曲播放量
    public void updatePlay_sum(int id){
        int sum = songMapper.getPlay_sum(id);
        songMapper.updatePlay_sum(id,sum);
    }
}
