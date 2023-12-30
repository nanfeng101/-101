package com.example.demo.controller;

import com.example.demo.entity.Mv;
import com.example.demo.entity.MvByCon;
import com.example.demo.service.MvService;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.Map;

@RestController
public class MvController {
    @Resource
    private MvService mvService;
    @GetMapping("/getMvDetail")
    public Map<String,Object> getMvDetail(MvByCon mvByCon){
        return mvService.getMvDetail(mvByCon);
    }
    @GetMapping("/classifymv")
    public List<Mv> classifymv(String name){
        return mvService.classifymv(name);
    }
    @PostMapping("/updateMvPic")
    public void updateMvPic(@RequestParam("file") MultipartFile myfile, @RequestParam("id") int id)throws Exception{
        String path = ResourceUtils.getURL("classpath:").getPath() + "static";
        String fileName = myfile.getOriginalFilename();
        String giamg = fileName;
        File filePath = new File(path + File.separator + fileName);
        myfile.transferTo(filePath);
        mvService.updateMvPic(giamg,id);
    }
    @PostMapping("/addMv")
    public String addMv(@RequestBody Mv mv)throws Exception{
        return mvService.addMv(mv);
    }
    @GetMapping("/selectIdMv")
    public Mv selectIdMv(int id){
        return mvService.selectIdMv(id);
    }
    @GetMapping("/deleteMv")
    public String deleteMv(int id)throws Exception{
        return mvService.deleteMv(id);
    }
    @GetMapping("/updateMv")
    public String updateMv(Mv mv){
        return mvService.updateMv(mv);
    }

    @PostMapping("/getAndroidMv")
    //安卓方法
    public List<Mv> getAndroidMv(int id){
        return mvService.getAndroidMvDetail();
    }
}
