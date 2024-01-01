package com.example.demo.dao.text;

import com.example.demo.enity.Text;
import com.example.demo.enity.Text_biaoqian;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManageTextMapper {
    List<Text> getAllTextDetail();
    List<Text_biaoqian> getTextBiaoqian(int id);
    List<Text> getHottext();
    int updateText(Text text);
    void deleteText_biaoqian(int id);
    Text selectIdText(int id);
    List<Text> selectColleSumText();
}
