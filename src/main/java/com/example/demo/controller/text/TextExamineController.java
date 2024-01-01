package com.example.demo.controller.text;

import com.example.demo.enity.User_chat;
import com.example.demo.service.text.TextExamineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TextExamineController {
    @Resource
    private TextExamineService textExamineService;
    @GetMapping("/updateExamine")
    public String updateExamine(int id){
        return textExamineService.updateExamine(id);
    }
    @PostMapping("/closeReason")
    public String closeReason(@RequestBody User_chat user_chat){
        return textExamineService.closeReason(user_chat);
    }
}
