package com.example.demo.controller.user;

import com.example.demo.dao.user.UserTextMapper;
import com.example.demo.enity.Text;
import com.example.demo.enity.Text_draft;
import com.example.demo.service.user.UserTextService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserTextConroller {
    @Resource
    private UserTextService userTextService;
    @GetMapping("/getUserIdText")
    public List<Text> getUserIdText(int id){
        return userTextService.getUserIdText(id);
    }
    @GetMapping("/selectDateText")
    public List<Text> selectDateText(int id){
        return userTextService.selectDateText(id);
    }
    @GetMapping("/selectViewText")
    public List<Text> selectViewText(int id){
        return userTextService.selectViewText(id);
    }
    @GetMapping("/getUserIdTextDraft")
    public List<Text_draft> getUserIdTextDraft(int id){
        return userTextService.getUserIdTextDraft(id);
    }
}
