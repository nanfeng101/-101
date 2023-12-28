package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.mapper.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SystemhomeServiceImpl implements SystemhomeService{
    @Resource
    private MvMapper mvMapper;
    @Resource
    private ConsumerMapper consumerMapper;
    @Resource
    private SongMapper songMapper;
    @Resource
    private SingerMapper singerMapper;
    @Resource
    private Song_listMapper songListMapper;
    @Resource
    private SystemhomeMapper systemhomeMapper;
    //获取用户总数
    public int getUserSum(){
        ConsumerByCon consumer = new ConsumerByCon();
        consumer.setAct("byNoCon");
        return consumerMapper.getConsumer(consumer).size();
    }
    //获取歌曲总数
    public int getSongSum(){
        SongByCon songByCon = new SongByCon();
        songByCon.setAct("byNoCon");
        return songMapper.getSongPage(songByCon).size();
    }
    //获取歌手总数
    public int getSingerSum(){
        SingerByCon singerByCon = new SingerByCon();
        singerByCon.setAct("byNoCon");
        return singerMapper.selectSingerPage(singerByCon).size();
    }
    //获取歌单总数
    public int getSong_listSum(){
        Song_listByCon song_listByCon = new Song_listByCon();
        song_listByCon.setAct("byNoCon");
        return songListMapper.getSong_listPage(song_listByCon).size();
    }
    public int selectUserSex(int sex){
        return systemhomeMapper.selectUserSex(sex).size();
    }
    public int selectSingerSex(int sex){
        return systemhomeMapper.selectSingerSex(sex).size();
    }
    public List<Integer> selectSingerLocation(){
        List<Integer> list1 = new ArrayList<Integer>();
        //['中国', '韩国', '意大利', '日本', '美国','西班牙','英国','瑞典']
        list1.add(systemhomeMapper.selectSingerLocation("中国").size());
        list1.add(systemhomeMapper.selectSingerLocation("韩国").size());
        list1.add(systemhomeMapper.selectSingerLocation("意大利").size());
        list1.add(systemhomeMapper.selectSingerLocation("日本").size());
        list1.add(systemhomeMapper.selectSingerLocation("美国").size());
        list1.add(systemhomeMapper.selectSingerLocation("西班牙").size());
        list1.add(systemhomeMapper.selectSingerLocation("英国").size());
        list1.add(systemhomeMapper.selectSingerLocation("瑞典").size());
        return list1;
    }
    public List<Integer> selectSong_listStyle(){
        List<Integer> list1 = new ArrayList<Integer>();
        //['华语', '粤语', '欧美', '日本', 'BGM', '轻音乐','乐器']
        list1.add(systemhomeMapper.selectSingerLocation1("华语").size());
        list1.add(systemhomeMapper.selectSingerLocation1("粤语").size());
        list1.add(systemhomeMapper.selectSingerLocation1("欧美").size());
        list1.add(systemhomeMapper.selectSingerLocation1("日本").size());
        list1.add(systemhomeMapper.selectSingerLocation1("BGM").size());
        list1.add(systemhomeMapper.selectSingerLocation1("轻音乐").size());
        list1.add(systemhomeMapper.selectSingerLocation1("乐器").size());
        return list1;
    }
    //获取歌曲和mv前12的播放量
    public Map<String,Object> getSongMvPlaySum(){
        List<Song> list1 = songMapper.getSongPlay();
        List<Mv> list2 = mvMapper.getMvPlay();
        Map<String,Object> map1 = new HashMap<String,Object>();
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
        List list3 = new ArrayList();  //用于存放歌曲和mv名字
        List list4 = new ArrayList();  //用于存放歌曲播放量
        List list5 = new ArrayList();  //用于存放mv播放量
        for(int i=0;i<12;i++){
            Song song = list1.get(i);
            Mv mv = list2.get(i);
            String str=null;
            str="歌曲:"+song.getName()+","+"MV:"+mv.getName();
            list3.add(str);
            list4.add(song.getPlay_sum());
            list5.add(mv.getPlay_sum());
        }
        map1.put("name",list3);
        map1.put("song",list4);
        map1.put("mv",list5);
        return map1;

    }


}
