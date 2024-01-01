package com.example.demo.dao.user;

import com.example.demo.enity.*;
import org.springframework.stereotype.Repository;
import org.w3c.dom.ls.LSException;

import java.util.Date;
import java.util.List;

@Repository
public interface UserDataMapper {
    User_view_sum getUserView(Date time,int text_id);
    void addview(Date time,int text_id);
    void updateTextView(int view,int id);
    void updateUser_view_sum(int sum,int id);

    User_dianzan_sum getUserDian(Date time,  int text_id);
    void adddian(Date time,int text_id);
    void updateTextDian(int view,int id);
    void updateUser_Dian_sum(int sum,int id);
    void addcollect(Date time,int text_id);
//    void updateTextCollect(int view,int id);
    void updateUser_Collect_sum(int sum,int id);
    User_collect_sum getUserCollect(Date time, int text_id);
    void addUser_collect(int user_id,int text_id,int collect_name_id);
    User_collect selectUseer_collect(int user_id,int text_id);
    List<Text> getUserIdTexsId(int id);
    List<User_fensi_data> getUserFensiLocation(int id);
    List<User_fensi> getUserFensiSum(int id);
    List<User_comment> getCommentsum(Date time,int id);
    List<User_comment> getTextCommentsum(int id);

}
