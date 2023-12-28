package com.example.demo.service;

import com.example.demo.entity.Consumer;
import com.example.demo.entity.ConsumerByCon;
import com.example.demo.entity.Song_list;

import java.util.List;
import java.util.Map;

public interface ConsumerService {
    Map<String,Object> getConsumerPage(ConsumerByCon consumerByCon);
    void updateAcator(String acator,int id);
    String addConsumer(Consumer consumer);
    String deleteConsumer(int id);
    Consumer getIdConsumer(int id);
    String updateConsumer(Consumer consumer);
    Map<String ,Object> selectConsumer(String select_name);
    Map<String,Object> userLogin(Consumer consumer);
    String shoucang(int userid,int songid);
    Map<String,Object> getMySong(int id);
    String userRegister(Consumer consumer);
    List<Song_list> getUserCreateSong_list(int id);
    Map<String,Object> getSingerDetail(int id);
}
