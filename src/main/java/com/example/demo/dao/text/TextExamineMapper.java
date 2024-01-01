package com.example.demo.dao.text;

import com.example.demo.enity.Text_draft;
import com.example.demo.enity.User_chat;
import org.springframework.stereotype.Repository;

@Repository
public interface TextExamineMapper {
    int updateExamine(int id);
    void addUserChat(User_chat user_Chat);
    void deleteExamine(int id);
    void deleteText(int id);
    Text_draft selectIdText(int id);
    void addTextDraft(Text_draft textDraft);
}
