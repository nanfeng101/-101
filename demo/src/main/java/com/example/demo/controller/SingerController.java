package com.example.demo.controller;

import com.example.demo.entity.Singer;
import com.example.demo.entity.SingerByCon;
import com.example.demo.service.SingerService;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@RestController
public class SingerController {
    @Resource
    private SingerService singerService;
    @GetMapping("/getSingerPage")
    public Map<String,Object> selectSingerPage(SingerByCon singerByCon){
        System.out.println(singerByCon.getSex());
        return singerService.selectSingerPage(singerByCon);
    }
    @GetMapping("/selectSinger")
    public Map<String,Object> selectSinger(String name){
        return singerService.selectSinger(name);
    }
    @PostMapping("/updatePic")
    public void updatePic(@RequestParam("file") MultipartFile myfile, @RequestParam("id") int id)throws Exception{
        String path = ResourceUtils.getURL("classpath:").getPath() + "static";
        String fileName = myfile.getOriginalFilename();
        String giamg = fileName;
        File filePath = new File(path + File.separator + fileName);
        myfile.transferTo(filePath);
        singerService.updatePic(giamg,id);
    }
    @PostMapping("/addSinger")
    public String addSinger(@RequestBody Singer singer){
        return singerService.addSinger(singer);
    }
    @GetMapping("/deleteSinger")
    public String deleteSinger(int id){
        return singerService.deleteSinger(id);
    }
    @GetMapping("/getIdSinger")
    public Singer getIdSinger(int id){
        return singerService.getIdSinger(id);
    }
    @PostMapping("/updateSinger")
    public String updateSinger(@RequestBody Singer singer){
        return singerService.updateSinger(singer);
    }
    @GetMapping("/getSong_to_qiantai")
    public Map<String,Object> getSong_to_qiantai(int id){
        return singerService.getSong_to_qiantai(id);
    }
}
