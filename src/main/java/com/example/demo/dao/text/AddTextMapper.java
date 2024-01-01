package com.example.demo.dao.text;

import com.example.demo.enity.*;
import org.springframework.stereotype.Repository;

@Repository
public interface AddTextMapper {
    int addTextContent(Text text);
    int addTextDraft(Text text);
    SearchId searchTextId(String biaoti);
    void addBioaqian(String name);
    SearchBiaqianId searchBiaoqian(String name);
    void addText_biaoqian(int id1,int id2);
    Text_type searchtype(String type);
    void addtype(String type);
    void updateType(int id1,int id2);
    Text_biaoqian searchText_biaoqian(int id1,int id2);
    void updateText_biaoqian(int id1,int id2);
    void addTextStatusExamine(int id1,int id2);
    Text_draft selectTextDraft(String biaoti);
    void deleteTextDraft(int id);
}
