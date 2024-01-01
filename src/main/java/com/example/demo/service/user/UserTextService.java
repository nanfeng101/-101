package com.example.demo.service.user;

import com.example.demo.enity.Text;
import com.example.demo.enity.Text_draft;

import java.util.List;

public interface UserTextService {
    List<Text> getUserIdText(int id);
    List<Text> selectDateText(int id);
    List<Text> selectViewText(int id);
    List<Text_draft> getUserIdTextDraft(int id);
}
