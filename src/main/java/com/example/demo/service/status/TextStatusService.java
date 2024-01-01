package com.example.demo.service.status;

import com.example.demo.enity.Text;

import java.util.List;

public interface TextStatusService {
    List<Text> getTouTiaoStatus(int current, int pageSize);
    List<Text> getReiDianStatus(int current, int pageSize);
    List<Text> getExamineStatus(int id);
    List<Text> getAllExamineStatus();
    int updateStatusToutiao(int n,int id);
    int updateStatusRedian(int n,int id);
    public List<Text> searchBiaotiText(String str);
}
