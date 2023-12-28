package com.example.demo.mapper;

import com.example.demo.entity.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumerMapper {
    List<Consumer> getConsumer(ConsumerByCon consumerByCon);
    void updateAcator(String acator,int id);
    int addConsumer(Consumer consumer);
    int deleteConsumer(int id);
    Consumer getIdConsumer(int id);
    int updateConsumer(Consumer consumer);
    List<Consumer> selectConsumer(String select_name);
    Consumer userLogin(Consumer consumer);
    int shoucang(int userid,int songid);
    List<Collect> getMySong(int id);
    Song_list getMySong_list(int id);
    Mv getMyMv(int id);
    Collect selectShoucang(int userid,int songid);
    int userRegister(Consumer consumer);
    String getIdUserName(int id);
    List<Song_list> getUserCreateSong_list(int id);
    Consumer getFans(int id2);
    Singer getAttention(int id1);
    Consumer getAttention_user(int id3);
}
