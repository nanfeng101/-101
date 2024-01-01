package com.example.demo.controller.user;

import com.example.demo.enity.Photo;
import com.example.demo.service.user.PhotoService;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

@RestController
public class PhotoController {
    @Resource
    private PhotoService photoService;
    @GetMapping("/getPhoto")
    public List<Photo> getPhoto(){
        return photoService.getPhoto();
    }
    @PostMapping("/addPhoto")
    public String addPhoto(@RequestBody Photo photo){
        return photoService.addPhoto(photo);
    }
    @GetMapping("/deletePhoto")
    public String deletePhoto(int id,String pic)throws Exception{
        String path = ResourceUtils.getURL("classpath:").getPath() + "static/"+pic;
        File file = new File(path);
        file.delete();
        return photoService.deletePhoto(id);
    }
}
