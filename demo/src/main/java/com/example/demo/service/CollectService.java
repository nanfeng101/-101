package com.example.demo.service;

import java.util.Map;

public interface CollectService {
    Map<String,Object> getCollect(int id);
    String deleteCollect(int id);
    String Song_listShouCang(int id1,int id2);
    String MvShouCang(int id1,int id2);
    String Attention_singer(int id1,int id2);


}
