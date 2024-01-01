package com.example.demo.service.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserDataService {
    void updateview(int user_id,int text_id,int view_sum);
    Map<String, Object> getDateView(int user_id,int text_id);

    void updatedian(int user_id,int text_id,int view_sum);
    Map<String, Object> getDateDian(int user_id,int text_id);
    String updatecollect(int user_id,int text_id,int view_sum,int collect_name_id);
    Map<String, Object> getDateCollect(int user_id,int text_id);

    Map<String,Object> getAllData(int user_id);
    Map<String,Object> getUserFensiLocation(int id);
    Map<String,Object> getCommentsum(int id);
    int getTextCommentSum(int id);

}
