package com.example.demo.controller;

import com.example.demo.service.CollectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class CollectController {
    @Resource
    private CollectService collectService;
    @GetMapping("/getCollect")
    public Map<String,Object> getCollect(int id){
        return collectService.getCollect(id);
    }
    @GetMapping("/deleteCollect")
    public String deleteCollect(int id){
        return collectService.deleteCollect(id);
    }
    @GetMapping("/Song_listShouCang")
    public String Song_listShouCang(int id1,int id2){
        return collectService.Song_listShouCang(id1,id2);
    }
    @GetMapping("/MvShouCang")
    public String MvShouCang(int id1,int id2){
        return collectService.MvShouCang(id1,id2);
    }
    @GetMapping("/Attention_singer")
    public String Attention_singer(int id1,int id2){
        return collectService.Attention_singer(id1,id2);
    }
}
