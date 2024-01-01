package com.example.demo.controller.text;

import com.example.demo.enity.Text;
import com.example.demo.service.text.AddTextService;
import com.example.demo.vo.FormData;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class AddTextController {
    @Resource
    private AddTextService addTextService;
    @PostMapping("/uploadCoverPic")
    public String uploadCoverPic(@RequestParam(value = "image") MultipartFile file)throws Exception{
        return addTextService.uploadCoverPic(file);
    }
    @PostMapping("/uploadBiaoTiPic")
    public String uploadBiaoTiPic(@RequestParam("file") MultipartFile file)throws Exception{
        return addTextService.uploadCoverPic(file);
    }
    //添加文章
    @PostMapping("/addTextContent")
    public int addTextContent(@RequestBody Text text){
        Date date = new Date();
        text.setCreate_time(date);
        return addTextService.addTextContent(text);
    }
    @GetMapping("/addBioaqian")
    public void addBioaqian(int biaoti,String name){
        addTextService.addBioaqian(biaoti,name);
    }
    @GetMapping("/addTextType")
    public void addTextType(int id,String type){
        addTextService.addTextType(id,type);
    }
    @PostMapping("/addTextDraft")
    public int addTextDraft(@RequestBody Text text){
        System.out.println(text.getContent());
        return addTextService.addTextDraft(text);
    }
}
