package com.example.demo.dao.status;

import com.example.demo.enity.Text;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextStatusMapper {
    List<Text> getTouTiaoStatus(int current,int pageSize);
    List<Text> getReiDianStatus(int current,int pageSize);
    List<Text> getExamineStatus(int id);
    List<Text> getAllExamineStatus();
    int updateStatusToutiao(int n,int id);
    int updateStatusRedian(int n,int id);
    List<Text> searchBiaotiText(String str);
}
