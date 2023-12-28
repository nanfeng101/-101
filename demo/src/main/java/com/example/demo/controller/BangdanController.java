package com.example.demo.controller;

import com.example.demo.entity.Bangdan;
import com.example.demo.entity.Song;
import com.example.demo.entity.SongByCon;
import com.example.demo.service.BangdanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class BangdanController {
    @Resource
    private BangdanService bangdanService;
    @GetMapping("/getBangSong")
    public List<Bangdan> getBangSong(String bangname){
        return bangdanService.getBangSong(bangname);
    }
    @GetMapping("/getTopSong")
    public Map<String, Object> getTopSong(SongByCon songByCon){
        return bangdanService.getTopSong(songByCon);
    }


    //
    @PostMapping("/getAndroidBangSong")
    public List<Bangdan> getAndroidBangSong(String bangname){
        return bangdanService.getBangSong(bangname);
    }

}
