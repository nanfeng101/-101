package com.example.demo.service.text;

import com.example.demo.enity.Text;

import java.util.List;
import java.util.Map;

public interface ManageTextService {
    Map<String,Object> getAllTextDetail();
    List<String> getTextBiaoqian(int id);
    List<Text> getHottext();
    int updateText(Text text);
    Text selectIdText(int id);
}
