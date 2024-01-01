package com.example.demo.dao.user;

import com.example.demo.enity.Text;
import com.example.demo.enity.Text_draft;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface UserTextMapper {
    List<Text> getUserIdText(int id);
    List<Text> selectDateText(int id);
    List<Text> selectViewText(int id);
    List<Text_draft> getUserIdTextDraft(int id);

}
