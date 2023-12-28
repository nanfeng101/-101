package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.mapper.ConsumerMapper;
import com.example.demo.mapper.SongMapper;
import com.example.demo.too.HuffmanCode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ConsumerServiceImpl implements ConsumerService{
    @Resource
    private ConsumerMapper consumerMapper;
    @Resource
    private SongMapper songMapper;
    //根据分页页码查询用户
    public Map<String,Object> getConsumerPage(ConsumerByCon consumerByCon){
        Map<String,Object> map1 = new HashMap<String,Object>();
        int sum=5*(consumerByCon.getCurrentPage()-1);
        consumerByCon.setCurrentPage(sum);
        consumerByCon.setAct("byCon");
        map1.put("consumer",consumerMapper.getConsumer(consumerByCon));
        consumerByCon.setAct("byNoCon");
        map1.put("total",consumerMapper.getConsumer(consumerByCon).size());
        return map1;
    }
    //更新用户图片
    public void updateAcator(String acator,int id){
        consumerMapper.updateAcator(acator,id);
    }
    //添加用户
    public String addConsumer(Consumer consumer){
        Date now = new Date();
        consumer.setCreate_time(now);
        String str1 = consumer.getPassword();
        byte[] byte1 = str1.getBytes();
        HuffmanCode huffmanCode = new HuffmanCode();
        byte[] byte2 = huffmanCode.huffmanZip(byte1);
        String str2 = new String(byte2);
        int a=huffmanCode.getJiequ();
        consumer.setPassword_jie(a);
        consumer.setPassword(str2);
        if (consumer.getUsername()!=null) {
            if (consumerMapper.addConsumer(consumer) > 0) {
                return "ok";
            }
        }
        return "no";
    }
    //删除用户
    public String deleteConsumer(int id){
        if(consumerMapper.deleteConsumer(id)>0){
            return "ok";
        }
        return "no";
    }
    //根据用户id查询用户信息
    public Consumer getIdConsumer(int id){
        Consumer consumer = consumerMapper.getIdConsumer(id);
//        String str1=consumer.getPassword();
//        System.out.println(str1);
//        byte[] bytes = str1.getBytes();
//        HuffmanCode huffmanCode = new HuffmanCode();
//        huffmanCode.setJiequ(consumer.getPassword_jie());
//        String str2 = huffmanCode.decote1(bytes);
//        System.out.println(str2);
//        consumer.setPassword(str2);
        return consumer;
    }
    //修改用户信息
    public String updateConsumer(Consumer consumer){
        String str1 = consumer.getPassword();
        byte[] byte1 = str1.getBytes();
        HuffmanCode huffmanCode = new HuffmanCode();
        byte[] byte2 = huffmanCode.huffmanZip(byte1);
        String str2 = new String(byte2);
        int a=huffmanCode.getJiequ();
        consumer.setPassword_jie(a);
        consumer.setPassword(str2);

        if(consumerMapper.updateConsumer(consumer)>0){
            return "ok";
        }
        return "no";
    }
    //根据用户昵称搜索用户
    public Map<String,Object> selectConsumer(String select_name){
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("consumer",consumerMapper.selectConsumer(select_name));
        map1.put("total",consumerMapper.selectConsumer(select_name).size());
        return map1;
    }
    //用户登录
    public Map<String,Object> userLogin(Consumer consumer){
        String str1 = consumer.getPassword();
        byte[] byte1 = str1.getBytes();
        HuffmanCode huffmanCode = new HuffmanCode();
        byte[] byte2 = huffmanCode.huffmanZip(byte1);
        String str2 = new String(byte2);
        consumer.setPassword(str2);
        Map<String,Object> map1 = new HashMap<String,Object>();
        Consumer con = consumerMapper.userLogin(consumer);
        if(con!=null){
            map1.put("consumer",con);
            map1.put("islogin",1);
            return map1;
        }
        else{
            map1.put("islogin",0);
            return map1;
        }
    }
    //用户收藏歌曲
    public String shoucang(int userid,int songid){
        if(consumerMapper.shoucang(userid,songid)>0){
            return "ok";
        } else if (consumerMapper.selectShoucang(userid,songid)!=null) {
            return "in";
        }
        else{
            return "no";
        }
    }
    //获得收藏歌曲
    public Map<String,Object> getMySong(int id){
        Map<String,Object> map1 = new HashMap<String,Object>();
        if(id==0){
            return map1;
        }
        int m1=0;
        int n1=0;
        int c1=0;
        List<Song> list1 = new ArrayList<Song>();
        List<Collect> list2 = consumerMapper.getMySong(id);
        List<Song_list> list3 = new ArrayList<Song_list>();
        List<Mv> list4 = new ArrayList<Mv>();
        for(Collect item : list2){
            if(item.getSong_id()!=0) {
                m1++;
                Song song1 = songMapper.getIdSong(item.getSong_id());
                String name1 = song1.getName();
                char[] name2 = name1.toCharArray();
                String name3 = "";
                String name4 = "";
                int m = 0;
                for (int i = 0; i < name2.length; i++) {
                    if (name2[i] == '-') {
                        m = i;
                        break;
                    }
                    name3 = name3 + name2[i];
                }
                song1.setSinger_name(name3);
                for (int j = m + 1; j < name2.length; j++) {
                    name4 = name4 + name2[j];
                }
                song1.setName(name4);
                list1.add(song1);
            }
            if(item.getSong_list_id()!=0){
                n1++;
                Song_list songList = consumerMapper.getMySong_list(item.getSong_list_id());
                String user = consumerMapper.getIdUserName(songList.getUsername_id());
                songList.setUsername(user);
                list3.add(songList);
            }
            if(item.getMv_id()!=0){
                c1++;
                Mv mv = consumerMapper.getMyMv(item.getMv_id());
                list4.add(mv);
            }
        }
        map1.put("song",list1);
        map1.put("song_list",list3);
        map1.put("mv",list4);
        map1.put("songsum",m1);
        map1.put("song_listsum",n1);
        map1.put("mv_sum",c1);
        return map1;
    }
    //用户注册
    public String userRegister(Consumer consumer){
        if(consumer.getPassword()!=null && consumer.getUsername()!=null) {
            Date now = new Date();
            consumer.setCreate_time(now);
            String str1 = consumer.getPassword();
            byte[] byte1 = str1.getBytes();
            HuffmanCode huffmanCode = new HuffmanCode();
            byte[] byte2 = huffmanCode.huffmanZip(byte1);
            String str2 = new String(byte2);
            int a=huffmanCode.getJiequ();
            consumer.setPassword_jie(a);
            consumer.setPassword(str2);
            if (consumerMapper.userRegister(consumer) > 0) {
                return "ok";
            } else {
                return "no";
            }
        }
        else {
            return "no";
        }
    }
    //获得用户创建歌单
    public List<Song_list> getUserCreateSong_list(int id){
        return consumerMapper.getUserCreateSong_list(id);
    }
    //获得用户的关注和粉丝
    public Map<String,Object> getSingerDetail(int id){
        Map<String,Object> map1 = new HashMap<String,Object>();
        List<Collect> list1 = consumerMapper.getMySong(id);
        List<Singer> list2 = new ArrayList<Singer>();
        List<Consumer> list5 = new ArrayList<Consumer>();
        List<Consumer> list3 = new ArrayList<Consumer>();
        int m1=0;
        int n1=0;
        int c1=0;
        for(Collect item : list1){
            if(item.getSinger_id()!=0){
                m1++;
                int id1=item.getSinger_id();
                Singer singer = consumerMapper.getAttention(id1);
                list2.add(singer);
            }
            if(item.getAttention_user_id()!=0){
                int id3=item.getAttention_user_id();
                Consumer consumer = consumerMapper.getFans(id3);
                list5.add(consumer);
                c1++;
            }
            if(item.getFans_id()!=0) {
                int id2 = item.getFans_id();
                Consumer consumer = consumerMapper.getFans(id2);
                list3.add(consumer);
                n1++;
            }
        }
        map1.put("singer",list2);
        map1.put("fans",list3);
        map1.put("consumer",list5);
        map1.put("singer_sum",m1+c1);
        map1.put("consumer_sum",n1);
        map1.put("aa",c1);
        return map1;

    }
}
